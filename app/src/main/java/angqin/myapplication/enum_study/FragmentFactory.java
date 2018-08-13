package angqin.myapplication.enum_study;


import java.util.HashMap;
import java.util.Map;

import angqin.myapplication.enum_study.base.BaseFragment;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class FragmentFactory {
    public static Map<FragmentEnunm, BaseFragment> cacheMap = new HashMap<FragmentEnunm, BaseFragment>();

    public static BaseFragment getFragment(FragmentEnunm fragmentEnunm) {
        if (cacheMap.get(fragmentEnunm) != null) {
            return cacheMap.get(fragmentEnunm);
        } else {
            switch (fragmentEnunm) {
                case CT_BLUETOOTH:
                    BluetoothFragment bluetoothFragment = new BluetoothFragment();
                    cacheMap.put(FragmentEnunm.CT_BLUETOOTH, bluetoothFragment);
                    break;
                case CT_EMV:
                    EmvFragment emvFragment = new EmvFragment();
                    cacheMap.put(FragmentEnunm.CT_EMV, emvFragment);
                    break;
                case CT_PINPAD:
                    PindFragment pindFragment = new PindFragment();
                    cacheMap.put(FragmentEnunm.CT_PINPAD, pindFragment);
                    break;
                case CT_DIAPLAY:
                    break;
                case CT_TERMINAL:
                    break;
                case CT_CARDERREADER:
                    break;
                default:
                    BluetoothFragment bluetoothFragment1 = new BluetoothFragment();
                    cacheMap.put(FragmentEnunm.CT_BLUETOOTH, bluetoothFragment1);
            }
        }
        return cacheMap.get(fragmentEnunm);

    }


}
