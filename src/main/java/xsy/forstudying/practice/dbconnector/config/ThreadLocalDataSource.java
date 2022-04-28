//package xsy.forsutying.practice.dbconnector.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.xml.crypto.Data;
///**
// * @author XuSiYu
// * @version 1.0
// * @date 2022-04-25 14:31
// **/
//@Slf4j
//public class ThreadLocalDataSource {
//    //使用threadLocal保证切换数据源时的线程安全 不会在多线程的情况下导致切换错数据源
//    private static final ThreadLocal<String> TYPE = new ThreadLocal<String>();
//
//    /**
//     * 修改当前线程内的数据源id
//     * @param key
//     */
//    public static void setLocalSource(String key){
//        TYPE.set(key);
//    }
//
//    /**
//     * 获取当前线程内的数据源类型
//     * @return
//     */
//    public static String getLocalSource(){
//        return TYPE.get();
//    }
//
//    /**
//     * 清空ThreadLocal中的TYPE
//     */
//    public void clear(){
//        TYPE.remove();
//    }
//}
