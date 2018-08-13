package angqin.myapplication.builder;

/**
 * 作者：${lixuebin} on 2018/1/31 17:28
 * 邮箱：2072301410@qq.com
 * tip :建造者模式
 *  < 理解： 1/ 就是单独的来对一个对象进行构造，将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 *           2/ 建造模式是将复杂的内部创建封装在内部，对于外部调用的人来说，只需要传入建造者和建造工具，对于内部是如何建造成成品的，调用者无需关心。
 */
public class Config {
    private String name;
    private int age;
    private String sex;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static class  Builder {
        private String name;
        private int age;
        private String sex;
        private String number;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Config build(){
            Config config = new Config();
            config.name = name;
            config.sex = sex;
            config.age = age;
            config.number = number;
            return config;
        }
    }
}
