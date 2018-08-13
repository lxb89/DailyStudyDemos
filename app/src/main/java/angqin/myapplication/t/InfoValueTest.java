package angqin.myapplication.t;

/**
 * 作者：${lixuebin} on 2018/1/30 16:16
 * 邮箱：2072301410@qq.com
 */
public class InfoValueTest<S> {

    public InfoValueTest(S infoValue) {
        this.infoValue = infoValue;
    }

    public S getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(S infoValue) {
        this.infoValue = infoValue;
    }

    private S infoValue;


}
