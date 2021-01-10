package com.edu.reading.common.tree;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.edu.reading.common.tree.annotation.Child;
import com.edu.reading.common.tree.annotation.Children;
import com.edu.reading.common.tree.annotation.Leaf;
import com.edu.reading.common.tree.annotation.Level;
import com.edu.reading.common.tree.annotation.NodeCode;
import com.edu.reading.common.tree.annotation.Parent;
import com.edu.reading.common.tree.annotation.SettingParent;
import com.edu.reading.common.tree.config.TreeConfig;

/**
 * 
 * @ClassName: TreeEntry
 * @Description: 树形数据在内存中存储模型
 * @author zht zhanghaitao@reignwood.com
 * @date 2017年9月19日 下午1:31:40
 * @param <T>
 */

public abstract class TreeEntry<T extends TreeNode> {
	// 是否初始化
	private boolean initialized;
	// 树模型对应的实体class
	private Class<T> type;

	protected TreeConfig config;
	// 存储树型实体对象
	protected List<T> treeList = new ArrayList<T>();

	protected TreeLevelParser<T> parser;
	
	public boolean abc = false;

	public boolean isRoot(String code) {
		if (treeList == null || treeList.isEmpty()) {
			reload();
		}
		T t = treeList.get(0);
		if (t.getCode().equals(code)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		return treeList.isEmpty();
	}

	/**
	 * 初始化层次型代码字典的获取层次代码函数和获取子结点函数
	 */
	@SuppressWarnings("unchecked")
	public void initConfig() throws ClassNotFoundException {
		if (!initialized) {
			if (null == type) {
				if (StringUtils.isEmpty(config.getClazzType())) {
					throw new IllegalStateException("The type of TreeEnty is unkown.");
				} else {
					type = (Class<T>) Class.forName(config.getClazzType());
				}
			}
			if (config.getCascade()) {
				Method[] methods = type.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(NodeCode.class)) {
						config.setNodeCodeMethod(method);
					}
					if (method.isAnnotationPresent(Level.class)) {
						config.setLevelMethod(method);
					}
					if (method.isAnnotationPresent(Child.class)) {
						config.setAddChildMethod(method);
					}
					if (method.isAnnotationPresent(Parent.class)) {
						config.setParentMethod(method);
					}
					if (method.isAnnotationPresent(Leaf.class)) {
						config.setLeafMethod(method);
					}
					if (method.isAnnotationPresent(Children.class)) {
						config.setChildrenMethod(method);
					}
					if (method.isAnnotationPresent(SettingParent.class)) {
						config.setSettingParentMethod(method);
					}

				}

				if (!config.checkNotNull()) {
					throw new IllegalStateException("some method is not specified in [" + config.getDictName() + "]");
				}
			}
		}
	}

	public synchronized boolean reload() {
		initialized = false;
		treeList = new ArrayList<T>();
		initTreeList();
		return initialized;
	}

	public String getNewLevelCode(String parentCode) throws Exception {
		List<T> list = getDescendant("orgcode", parentCode, false);
		if (list != null && !list.isEmpty()) {
			T parent = list.get(0);
			if (parent != null) {
				List<? extends TreeNode> children = parent.getChildren();
				TreeNode child = children.get(children.size() - 1);
				String levelCode = "";
				if (child != null) {
					levelCode = child.getLevelCode();
				}
				if (StringUtils.isNotBlank(levelCode)) {
					return newLevelCode(levelCode);
				} else {
					return parent.getLevelCode() + "001";
				}
			}
		}
		return "";
	}

	private String newLevelCode(String lastLeveCode) {
		String result = "";
		if (StringUtils.isNotBlank(lastLeveCode)) {
			String temp = StringUtils.right(lastLeveCode, 3);
			try {
				// 未位纯数字
				Integer code = Integer.parseInt(temp);

				result = lastLeveCode.substring(0, lastLeveCode.length() - 1) + (code + 1);
			} catch (Exception e) {
				// 未位是字母A开始，代表10,层次码是三位一层
				// 001009如果再增加一个子结点其层次码为00100A
				String alpha = temp.substring(2, 3);
				if (StringUtils.isAlphanumeric(alpha)) {
					char a = alpha.charAt(0);
					result = lastLeveCode.substring(0, lastLeveCode.length() - 1) + String.valueOf((char) (a + 1));
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getTreeList @Description: 查询所有树结点 @param source @return
	 *         List<T> @throws
	 */
	protected abstract List<T> initTreeList();

	protected abstract List<T> getDataList();

	protected abstract void setConfig(TreeConfig config);

	protected abstract void setParser(TreeLevelParser<T> parser);

	public List<T> dataSource() {
		List<T> dataList = getDataList();
		treateNodeExtends(dataList);
		return dataList;
	}

	private void treateNodeExtends(List<T> dataList) {
		if (!CollectionUtils.isEmpty(dataList)) {
			Map<String, String> overrides = config.getOverrides();
			Map<String, String> mappings = config.getMappings();
			if (CollectionUtils.isEmpty(overrides) && CollectionUtils.isEmpty(mappings))
				return;
			
//			if (!CollectionUtils.isEmpty(mappings)) {
//				mappings.forEach((sourceField, mappingName) -> {
//					try {
//						dynamicAddAnnotationToField(config.getClazzType(), sourceField, mappingName);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//			}
			
			dataList.forEach(obj -> {
				// 处理属性值覆盖
				if (!CollectionUtils.isEmpty(overrides)) {
					overrides.forEach((sourceProp, targetProps) -> {
						Object value = null;
						try {
							value = this.invokeGetMethod(obj.getClass(), obj, sourceProp);
						} catch (Exception e) {
							e.printStackTrace();
						}

						for (String targetProp : targetProps.split(",")) {
							try {
								invokeSetMethod(obj.getClass(), obj, targetProp, String.valueOf(value), new Class<?>[]{String.class});
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}

				// 处理Json报文输出结点名映射
//				if (!CollectionUtils.isEmpty(mappings)) {
//					mappings.forEach((sourceField, mappingName) -> {
//						try {
//							this.dynamicAddAnnotationToField(obj.getClass().getName(), sourceField, mappingName);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					});
//				}
			});
		}
	}

	/**
	 * 
	 * @Title: searchNodeInTree @Description: 在全树上搜索所有满足条件的结点 @param propertyName
	 *         结点属性名 @param propertyValue 搜索目标值 @param likeSearch true:模糊搜索
	 *         false:精确搜索 @param source 树形结点的数据源 @param @throws Exception @return
	 *         List<T> @throws
	 */
	public List<T> searchNodeInTree(String propertyName, String targetValue, boolean likeSearch) throws Exception {
		if (!config.getCascade())
			throw new Exception(config.getDictName() + " isn't cascaded.");

		if (!isInitialized()) {
			parser.parseRelation(this, dataSource());
		}
		List<T> result = new ArrayList<T>();
		travelSearch(treeList, propertyName, targetValue, likeSearch, result);
		return result;
	}

	/**
	 * 
	 * @Title: searchNodeInTree @Description: 在全树上搜索所有满足条件的结点 @param propertyName
	 *         结点属性名 @param propertyValue 搜索目标值 @param likeSearch true:模糊搜索
	 *         false:精确搜索 @param source 树形结点的数据源 @param @throws Exception @return
	 *         List<T> @throws
	 */
	public List<T> searchNodeInTree(List<T> ptree, String propertyName, String targetValue, boolean likeSearch)
			throws Exception {
		List<T> result = new ArrayList<T>();
		travelSearch(ptree, propertyName, targetValue, likeSearch, result);
		return result;
	}

	private void travelSearch(List<T> deptList, String propertyName, String targetValue, boolean likeSearch,
			List<T> result) {
		for (T node : deptList) {
			String propertyValue = "";
			try {
				Object obj = invokeGetMethod(node.getClass(), node, propertyName);
				propertyValue = obj == null ? "" : obj.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (likeSearch) {
				// 模糊查询
				if (StringUtils.contains(propertyValue, targetValue)) {
					result.add(node);
				}
			} else {
				// 精确查询
				if (StringUtils.equals(propertyValue, targetValue)) {
					result.add(node);
				}
			}
			List<T> children = (List<T>) node.getChildren();
			if (children != null && !children.isEmpty()) {
				travelSearch(children, propertyName, targetValue, likeSearch, result);
			}
		}
	}

	/**
	 * 
	 * @Title: getDescendant
	 * @Description: 按树结点中的一个属性和属性值查询该结点下所有子结点,包括该结点
	 * @param propertyName
	 *            结点属性名
	 * @param propertyValue
	 *            结点属性值
	 * @return List<T>
	 * @throws Exception
	 */
	public List<T> getDescendant(String propertyName, Object propertyValue, boolean likeSearch) throws Exception {
		if (!config.getCascade())
			throw new Exception(config.getDictName() + " isn't cascaded.");

		if (!isInitialized()) {
			parser.parseRelation(this, dataSource());
		}
		List<T> descendant = new ArrayList<T>();
		List<T> parents = new ArrayList<T>();
		locateObjectInTree(treeList, new String[] { propertyName + ":" + propertyValue }, likeSearch, parents);
		travelDescendant(parents, descendant);
		return descendant;
	}

	public List<T> locateNode(String propertyName, Object propertyValue, boolean likeSearch) throws Exception {
		if (!config.getCascade())
			throw new Exception(config.getDictName() + " isn't cascaded.");

		if (!isInitialized()) {
			parser.parseRelation(this, dataSource());
		}
		List<T> result = new ArrayList<T>();
		locateObjectInTree(treeList, new String[] { propertyName + ":" + propertyValue }, likeSearch, result);
		return result;
	}

	public List<T> getAncestors(String propertyName, Object propertyValue, boolean likeSearch) throws Exception {
		List<T> ancestors = new ArrayList<T>();
		List<T> nodeList = locateNode(propertyName, propertyValue, likeSearch);
		if (nodeList != null && !nodeList.isEmpty()) {
			travelAncestors(nodeList.get(0), ancestors);
		}
		return ancestors;
	}

	/**
	 * 在层次型(树型)结点中定位某个结点
	 */
	@SuppressWarnings("unchecked")
	public void locateObjectInTree(List<T> objs, String[] filter, boolean likeSearch, List<T> result) throws Exception {
		// T[] objects = (T[]) objs.toArray();
		for (T obj : objs) {
			if (!isKeep(obj, filter, likeSearch)) {
				// 当前结点不满足过滤条件,递归查找其子结点
				List<T> children = (List<T>) invokeGetMethod(obj.getClass(), obj, "children");
				if (null != children && !children.isEmpty()) {
					locateObjectInTree(children, filter, likeSearch, result);
				}
			} else {
				// 当前结点满足过滤条件
				result.add(obj);
			}
		}
	}

	/**
	 * 对字典对象测试过滤条件 'a':b, 'c':d,...多个条件同时满足 a':b或'a':b,c,d,e...一个条件满足,b,c,d,e之间是或的关系
	 *
	 * @param obj
	 *            测试对象
	 * @param filter
	 *            过滤条件数组
	 * @return true 保留. false 删除
	 */
	private boolean isKeep(Object obj, String[] filter, boolean likeSearch) throws Exception {
		for (String filterItem : filter) {
			if (filterItem.indexOf("(@)") != -1) {
				// 多条件(AND)
				String[] innerStrs = filterItem.split("\\(@\\)");
				for (String innerStr : innerStrs) {
					String[] filterMap = innerStr.split(":");
					if (filterMap[1].indexOf(",") != -1) {
						// 多值(IN)
						String[] vs = filterMap[1].split(",");
						if (!this.isKeep(obj.getClass(), obj, filterMap[0], vs, likeSearch)) {
							return false;
						}
					} else {
						// 单值(=)
						if (!this.isKeep(obj.getClass(), obj, filterMap[0], filterMap[1], likeSearch)) {
							return false;
						}
					}
				}
				return true;
			} else {
				// 单条件
				String[] filterMap = filterItem.split(":");
				if (filterMap.length == 1) {
					return this.isKeep(obj.getClass(), obj, "id", "", likeSearch);
				}
				if (filterMap[1].indexOf(",") != -1) {
					// 多值(IN)
					String[] vs = filterMap[1].split(",");
					return this.isKeep(obj.getClass(), obj, filterMap[0], vs, likeSearch);

				} else {
					// 单值(=)
					return this.isKeep(obj.getClass(), obj, filterMap[0], filterMap[1], likeSearch);
				}
			}
		}
		return false;
	}

	/**
	 * 归递向上标志结点保留标志
	 * 
	 * @param org
	 *            当前结点
	 * @param reserved
	 *            是否保留
	 */
	public void reserveAncestors(TreeNode org, boolean reserved) {
		org.setReserved(reserved);
		TreeNode parent = org.getParent();
		if (parent != null) {
			reserveAncestors(parent, reserved);
		}
	}

	/**
	 * 归递向下标志结点保留标志
	 * 
	 * @param org
	 *            当前结点
	 * @param reserved
	 *            是否保留
	 */
	public void reserveChildren(TreeNode org, boolean reserved) {
		org.setReserved(reserved);
		List<? extends TreeNode> children = org.getChildren();
		if (children != null) {
			for (TreeNode child : children) {
				reserveChildren(child, reserved);
			}
		}
	}

	/**
	 * 从树根递归删除所有reserved=false的结点
	 * 
	 * @param node
	 */
	public void removeNode(TreeNode node) {
		List<? extends TreeNode> children = node.getChildren();
		if (children != null && !children.isEmpty()) {
			List<TreeNode> removedList = new ArrayList<TreeNode>();
			for (TreeNode child : children) {
				if (!child.isReserved()) {
					removedList.add(child);
				}
			}
			children.removeAll(removedList);

			for (TreeNode child : children) {
				removeNode(child);
			}
		}
	}

	private boolean isKeep(Class<?> clazz, Object obj, String propertyName, String[] values, boolean likeSearch)
			throws Exception {
		if (null == values || values.length == 0) {
			return false;
		}

		Object objValue = invokeGetMethod(clazz, obj, propertyName);
		if (null != objValue) {
			for (String value : values) {
				if (likeSearch) {
					if (StringUtils.contains(objValue.toString(), value))
						return true;
				} else {
					if (objValue.equals(value))
						return true;
				}
			}
		}
		return false;
	}

	private Object invokeGetMethod(Class<?> clazz, Object obj, String propertyName) throws Exception {
		String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		Method method = getMethod(methodName, clazz, null);
		return method.invoke(obj);
	}

	private void invokeSetMethod(Class<?> clazz, Object obj, String propertyName, Object value, Class<?>[] args) throws Exception {
		String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		Method method = getMethod(methodName, clazz, args);
		method.invoke(obj, value);
	}

	private Method getMethod(String methodName, Class<?> type, Class<?>[] args) throws NoSuchMethodException {
		Method result = null;
		if (null != type) {
			try {
				result = type.getDeclaredMethod(methodName, args);
			} catch (NoSuchMethodException e) {
				result = getMethod(methodName, type.getSuperclass(), args);
			}
		}
		return result;
	}

	private boolean isKeep(Class<?> clazz, Object obj, String propertyName, String value, boolean likeSearch)
			throws Exception {
		if (StringUtils.isEmpty(value)) {
			return false;
		}

		// String methodName = "get" + propertyName.substring(0, 1).toUpperCase() +
		// propertyName.substring(1);
		// Method method = getMethod(methodName, clazz, null);
		// Object objValue = method.invoke(obj);
		Object objValue = invokeGetMethod(clazz, obj, propertyName);

		if (null == objValue) {
			return false;
		}

		if (likeSearch) {
			// 模糊
			if (StringUtils.contains(objValue.toString(), value)) {
				return true;
			}
		} else {
			// 精确
			if(objValue instanceof String) {
				if (objValue.equals(value)) {
					return true;
				}				
			} else {
				if (objValue.toString().equals(value)) {
					return true;
				}
			}

		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void travelDescendant(List<T> parents, List<T> descendant) throws Exception {
		if (null != parents && !parents.isEmpty()) {
			for (T parent : parents) {
				descendant.add(parent);
				List<T> children = (List<T>) invokeGetMethod(parent.getClass(), parent, "children");
				if (null != children && !children.isEmpty()) {
					// Iterator<Object> it = children.iterator();
					// while (it.hasNext()) {
					// Object child = it.next();
					travelDescendant(children, descendant);
					// }
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void travelAncestors(T parent, List<T> ancestors) throws Exception {
		if (null != parent) {
			ancestors.add(parent);
			parent = (T) invokeGetMethod(parent.getClass(), parent, "parent");
			if (null != parent) {
				travelAncestors(parent, ancestors);
			}
		}
	}

	public void check() {
		if (!initialized) {

		}
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public TreeConfig getConfig() {
		return config;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public List<T> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<T> treeList) {
		this.treeList = treeList;
	}

	public TreeLevelParser<T> getParser() {
		return parser;
	}
}
