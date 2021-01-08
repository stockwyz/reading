package com.edu.reading.common.tree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.edu.reading.common.tree.config.TreeConfig;

/**
 * 	
 * @ClassName: TreeLevelParser 
 * @Description: 层次码型树解析
 * @author zht zhanghaitao@reignwood.com
 * @date 2017年9月12日 下午5:46:43 
 *
 */
@Service
public class TreeLevelParser<T extends TreeNode> {
	
    public void parseRelation(TreeEntry<T> entry, List<T> records) {
    	//数据库查询的实体集为空,无法初始化
    	if(records == null || records.isEmpty()) {
    		return ;
    	}
    	
    	synchronized(entry) {
        	//树实体已经初始化完成
        	if(entry.isInitialized()) {
        		return ;
        	}

            try {
        		//初始化当前类型函数
            	entry.initConfig();
                //递归构建层次
            	TreeConfig config = entry.getConfig();
                switch (entry.getConfig().getLevelType()) {
                    case LevelCode:
                        //按层次码方式构建树型结点,获取层次码
                        for (T record : records) {
                            Object obj = config.getLevelMethod().invoke(record);
                            if(null == obj) {
                            	entry.getTreeList().add(record);
                                continue;
                            }
                            leveCode(entry, record, obj.toString());
                        }
                        break;
                    case ParentRef:
                        //按引用父结点方式构建树型结点,获取父结点代码
                        List<T> treated = new LinkedList<T>();
                        for (T record : records) {
                            parentRef(entry, record, records, treated);
                        }
                        break;
                }
                
                //同级排序
                if(config.getSortSameLevel()) {
                	List<T> tree = entry.getTreeList();
                	if(tree != null && tree.size() > 0) {
                		T root = tree.get(0);
                		sortTreeNode(root);
                	}
                }
                
                //记录根到当前结点路径breadcrumb
                List<T> tree = entry.getTreeList();
                if(tree != null && tree.size() > 0) {
            		T root = tree.get(0);
            		root.setBreadcrumb(root.getId() + ":" + root.getName());
            		List<String> pathList = new ArrayList<String>();
            		breadcrumb(root, pathList);
            	}
                
                //初始化成功
                entry.setInitialized(true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                entry.setInitialized(false);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                entry.setInitialized(false);
            } catch(Exception e) {
            	 e.printStackTrace();
                 entry.setInitialized(false);
            }
    	}
    }
    
    @SuppressWarnings("unchecked")
	private void sortTreeNode(T root) {
    	List<? extends TreeNode> children = root.getChildren();
    	if(children != null && children.size() > 0) {
    		Collections.sort(children);
    		for(TreeNode node : children) {
    			sortTreeNode((T) node);
    		}
    	}
    }
    
    public void breadcrumb(TreeNode root, List<String> pathList) {
    	if(ObjectUtils.isEmpty(pathList)) {
    		pathList = new ArrayList<String>();
    	}
    	
    	List<? extends TreeNode> children = root.getChildren();
    	if(children != null && children.size() > 0) {
    		for(TreeNode node : children) {
    			storeParentNames(node, pathList);
    			Collections.reverse(pathList);
    			String breadcrumb = StringUtils.join(pathList, ",");
    			node.setBreadcrumb(breadcrumb);
    			pathList.clear();
    			breadcrumb(node, pathList);
    		}
    	}
    }
    
    private void storeParentNames(TreeNode node, List<String> pathList) {
    	pathList.add(node.getId() + ":" + node.getName());
    	TreeNode parent = node.getParent();
    	if(parent != null) {
    		storeParentNames(parent, pathList);
    	}
    }
    
    /**
     * 按层次码方式构建树型结点
     *
     * @param record
     * @param fieldValue
     */
    private void leveCode(TreeEntry<T> entry, T record, String fieldValue) {
        //层次码值非空,查找当前代码结点的父结点
        if (!StringUtils.isEmpty(fieldValue) && fieldValue.length() >= 3) {
        	List<T> treeList = entry.getTreeList();
            //层次码3位一增, 取出当前结点的父层次码
            String parentLevelCode = fieldValue.substring(0, fieldValue.length() - 3);
            if (StringUtils.isEmpty(parentLevelCode)) {
                //第一层结点，父结点的层次码为空, record是根结点
                if (treeList.contains(record)) {
                    //有两个层次码相同的根结点
                    throw new IllegalStateException("Same level code in code tree[" + entry.getConfig().getDictName() + "]");
                }
                else {
                    //在结果集中插入根结点
                	treeList.add(record);
                }
            } else if (treeList.size() == 0) {
                //非第一层结点,结果集中无根结点
            	treeList.add(record);
            } else {
                //非第一层结点,遍历value(List)查找record的父结点
                T parent = null;
                for (T from : treeList) {
                    parent = getParentForLevelCode(entry.getConfig(), from, parentLevelCode);
                    if (null == parent) {
                        //没找到,顺序查找value(List)的下一个结点层次
                        continue;
                    } else {
                        //找到record的父结点后退出
                        break;
                    }
                }

                if (parent == null) {
                    //循环遍历value后没有找到
                    // 父结点将根结点做父结点
                	treeList.add(record);
                    return;
                }

                attach(entry.getConfig(), parent, record);
            }
        }
    }
    
    /**
     * 在现存的树型结点中按targetLevelCode递归查找父结点
     *
     * @param node            被查找结点,其中可能在children集合中包含子结点
     * @param targetLevelCode 父结点层次码值,用来递归在树中查找父结点
     * @return
     */
    private T getParentForLevelCode(TreeConfig config, T node, Object targetLevelCode) {
        //取node结点的层次码
        Object value = null;
        try {
            value = config.getLevelMethod().invoke(node);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //node结点层次码非空
        if (null != value && value.toString().length() > 0) {
            if (!StringUtils.equals(value.toString(), targetLevelCode.toString())) {
                if (value.toString().length() > targetLevelCode.toString().length()) {
                    //当前结点的层次码大于目标结点的层次码,退出
                    return null;
                }

                //取node的子结点
                List<T> children = null;
                try {
                    children = (List<T>) config.getChildrenMethod().invoke(node);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                //递归查询比较子结点的层次码
                if (children != null) {
                    for (T child : children) {
                        T target = this.getParentForLevelCode(config, child, targetLevelCode);
                        if (target != null)
                            return target;
                    }
                }
            } else {
                //node结点的层次码与targetLevelCode
                return node;
            }
        }
        return null;
    }
    
    private void attach(TreeConfig config, T parent, T record) {
        try {
            //父子建立关联
            config.getAddChildMethod().invoke(parent, record);
            config.getSettingParentMethod().invoke(record, parent);
            //父结点expanded为true
//            if (isTreeExpanded())
//                expandedMethod.invoke(parent, true);
//            else
//                expandedMethod.invoke(parent, false);
            //父结点leaf为false
            config.getLeafMethod().invoke(parent, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 按引用父结点方式构建树型结点
     *
     * @param records
     */
    private void parentRef(TreeEntry<T> entry, T record, Collection<T> records, List<T> treated)
            throws InvocationTargetException, IllegalAccessException {
        if (!treated.contains(record)) {
        	TreeConfig config = entry.getConfig();
            //未被处理
            Object parentLevel = config.getParentMethod().invoke(record);
            if (parentLevel != null && !StringUtils.isEmpty(parentLevel.toString())) {
                //父结点字段非空,递归向上构建树
                T parent = getParentForParentRef(config, records, parentLevel);
                if(null != parent) {
                    attach(config, parent, record);
                    treated.add(record);
                    if (!treated.contains(parent)) {
                        parentRef(entry, parent, records, treated);
                    }
                } else {
                    //没找到父结点.
                    // 1第一层结点，数据库中无根结点
                    // 2数据错误, 找不到,加到树型结构第一层。
                    treated.add(record);
                    entry.getTreeList().add(record);
                }
            } else {
                //父结点字段非空,为根结点或第一层结点
                treated.add(record);
                entry.getTreeList().add(record);
            }
        }
    }
    
    /**
    *
    *在List集合中查找levelcode为指定值的记录
    * @param records list集合
    * @param targetParentLevelCode 父结点的层次码
    * @return
    */
   private T getParentForParentRef(TreeConfig config, Collection<T> records, Object targetParentLevelCode) {
       T result =null;
       for (T obj : records) {
           //取obj结点的层次码
           Object value = null;
           try {
               value = config.getNodeCodeMethod().invoke(obj);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }

           if (null == value && null == targetParentLevelCode) {
              result = (T) obj;
           } else if (null != value && null != targetParentLevelCode) {
               if (StringUtils.equals(value.toString(), targetParentLevelCode.toString())) {
                   result = (T) obj;
               }
           }
       }
       return result;
   }
}
