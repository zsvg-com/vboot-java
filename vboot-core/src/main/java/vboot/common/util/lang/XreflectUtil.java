package vboot.common.util.lang;

import vboot.common.util.obj.AlterPropertyInfo;
import vboot.common.util.obj.PropertyModelInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class XreflectUtil {

    public static void print(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println("---------------------------------");
        for (Field field:fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName()+":"+field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  比较两个对象属性值是否相同
     *  如果不同返回修改过的属性信息
     * @param oldObj
     * @param newObj
     * @param ignoreProperties
     * @param <T>
     * @return 修改过的属性字段
     */
    public static <T> List<AlterPropertyInfo> getDifferentProperty(T oldObj , T newObj , String... ignoreProperties){
        if (oldObj != null && newObj != null) {
            // 比较全部字段
            if (ignoreProperties == null || ignoreProperties.length > 0) {
                if (oldObj.equals(newObj)) {
                    return Collections.emptyList();
                }
            }
            List<PropertyModelInfo> oldObjectPropertyValue = getObjectPropertyValue(oldObj, ignoreProperties);
            if (!CollectionUtils.isEmpty(oldObjectPropertyValue)) {
                List<AlterPropertyInfo> modifiedPropertyInfos = new ArrayList<>(oldObjectPropertyValue.size());

                List<PropertyModelInfo> newObjectPropertyValue = getObjectPropertyValue(newObj, ignoreProperties);
                Map<String , Object> objectMap = new HashMap<>(newObjectPropertyValue.size());
                // 获取新对象所有属性值
                for (PropertyModelInfo propertyModelInfo : newObjectPropertyValue) {
                    String prnam = propertyModelInfo.getPrnam();
                    Object value = propertyModelInfo.getValue();
                    objectMap.put(prnam , value);
                }

                for (PropertyModelInfo propertyModelInfo : oldObjectPropertyValue) {
                    String prnam = propertyModelInfo.getPrnam();
                    Object value = propertyModelInfo.getValue();
                    if (objectMap.containsKey(prnam)) {
                        Object newValue = objectMap.get(prnam);
                        AlterPropertyInfo modifiedPropertyInfo = new AlterPropertyInfo();
//                        System.out.println("----------------------");
//                        System.out.println(propertyName);
//                        System.out.println(value);
//                        System.out.println(newValue);
                        if (value != null && newValue != null) {
                            if(value instanceof BigDecimal){
                                if ((new BigDecimal(value.toString())).compareTo(new BigDecimal(newValue.toString()))!=0) {
                                    modifiedPropertyInfo.setPrnam(prnam);
                                    modifiedPropertyInfo.setOlval(value);
                                    modifiedPropertyInfo.setNeval(newValue);
                                    modifiedPropertyInfos.add(modifiedPropertyInfo);
                                }
                            }else{
                                if (!(value.toString()).equals(newValue.toString())) {
                                    modifiedPropertyInfo.setPrnam(prnam);
                                    modifiedPropertyInfo.setOlval(value);
                                    modifiedPropertyInfo.setNeval(newValue);
                                    modifiedPropertyInfos.add(modifiedPropertyInfo);
                                }
                            }
                        }else if ((XstringUtil.isNotBlank(""+value)&& newValue == null) ||(value== null && XstringUtil.isNotBlank(""+newValue))){
                            modifiedPropertyInfo.setPrnam(prnam);
                            modifiedPropertyInfo.setOlval(value);
                            modifiedPropertyInfo.setNeval(newValue);
                            modifiedPropertyInfos.add(modifiedPropertyInfo);
                        }
                    }
                }
                return modifiedPropertyInfos;
            }
        }
        return Collections.emptyList();
    }


    /**
     *  通过反射获取对象的属性名称、getter返回值类型、属性值等信息
     * @param obj
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static  <T> List<PropertyModelInfo> getObjectPropertyValue(T obj , String... ignoreProperties){
        if (obj != null) {

            Class<?> objClass = obj.getClass();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(objClass);
            List<PropertyModelInfo> modelInfos = new ArrayList<>(propertyDescriptors.length);

            List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method readMethod = propertyDescriptor.getReadMethod();
                String name = propertyDescriptor.getName();
                if (readMethod != null && (ignoreList == null || !ignoreList.contains(name))) {
                    Object invoke = null;
                    Class<?> returnType = readMethod.getReturnType();
                    try {
                        invoke = readMethod.invoke(obj);
                        PropertyModelInfo propertyModelInfo = new PropertyModelInfo();
                        propertyModelInfo.setPrnam(name);
                        propertyModelInfo.setValue(invoke);
                        propertyModelInfo.setRetyp(returnType);
                        modelInfos.add(propertyModelInfo);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("反射获取类【"+objClass.getName()+"】方法异常，");
                    }
                }
            }
            return modelInfos;
        }
        return Collections.emptyList();
    }
}
