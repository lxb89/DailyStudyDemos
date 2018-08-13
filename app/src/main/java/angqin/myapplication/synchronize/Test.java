package angqin.myapplication.synchronize;

/**
 * 作者：${lixuebin} on 2018/1/31 15:20
 * 邮箱：2072301410@qq.com
 * tip： 双重锁机制（单例模式）---->>>解决线程安全问题
 */
public class Test {
    private static Test instance;
    private Test() {
    }

    public static Test getInstance() {
        //先检查实例是否存在，如果不存在才进入同步块
        if (instance == null) {
            //同步块，线程安全的地创建实例
            synchronized (Test.class) {
                //再次检查实例是否存在，如果不存在就创建实例
                if (instance == null) {
                    instance = new Test();
                }
            }
        }
        return instance;
    }
}
