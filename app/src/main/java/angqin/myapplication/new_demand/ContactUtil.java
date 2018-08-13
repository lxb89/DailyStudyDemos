package angqin.myapplication.new_demand;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lixuebin} on 2018/7/16.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class ContactUtil {
    public List<ContactsContract.Contacts> list;
    private Context context;
    private JSONObject contactData;
    private JSONObject jsonObject;

    public ContactUtil(Context context) {
        this.context = context;
    }

    // ContactsContract.Contacts.CONTENT_URI= content://com.android.contacts/contacts;
    // ContactsContract.Data.CONTENT_URI = content://com.android.contacts/data;

    /**
     * 获取联系人信息，并把数据转换成json数据
     *
     * @return
     * @throws JSONException
     */
    public String getContactInfo() throws JSONException {
        list = new ArrayList<ContactsContract.Contacts>();
        contactData = new JSONObject();
        String mimetype = "";
        int oldrid = -1;
        int contactId = -1;
        // 1.查询通讯录所有联系人信息，通过id排序，我们看下android联系人的表就知道，所有的联系人的数据是由RAW_CONTACT_ID来索引开的
        // 所以，先获取所有的人的RAW_CONTACT_ID
        Uri uri = ContactsContract.Data.CONTENT_URI; // 联系人Uri；
        Cursor cursor = context.getContentResolver().query(uri,
                null, null, null, ContactsContract.Data.RAW_CONTACT_ID);
        int numm = 0;
        while (cursor.moveToNext()) {
            contactId = cursor.getInt(cursor
                    .getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
            if (oldrid != contactId) {
                jsonObject = new JSONObject();
                contactData.put("contact" + numm, jsonObject);
                numm++;
                oldrid = contactId;
            }
            mimetype = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)); // 取得mimetype类型,扩展的数据都在这个类型里面
            // 1.1,拿到联系人的各种名字
            if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE.equals(mimetype)) {
                cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
                String prefix = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PREFIX));
                jsonObject.put("prefix", prefix);
                String firstName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                jsonObject.put("firstName", firstName);
                String middleName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME));
                jsonObject.put("middleName", middleName);
                String lastname = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                jsonObject.put("lastname", lastname);
                String suffix = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.SUFFIX));
                jsonObject.put("suffix", suffix);
                String phoneticFirstName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_FAMILY_NAME));
                jsonObject.put("phoneticFirstName", phoneticFirstName);

                String phoneticMiddleName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_MIDDLE_NAME));
                jsonObject.put("phoneticMiddleName", phoneticMiddleName);
                String phoneticLastName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_GIVEN_NAME));
                jsonObject.put("phoneticLastName", phoneticLastName);
            }
            // 1.2 获取各种电话信息
            if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE.equals(mimetype)) {
                int phoneType = cursor
                        .getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)); // 手机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                    String mobile = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("mobile", mobile);
                }
                // 住宅电话
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_HOME) {
                    String homeNum = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("homeNum", homeNum);
                }
                // 单位电话
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK) {
                    String jobNum = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("jobNum", jobNum);
                }
                // 单位传真
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK) {
                    String workFax = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("workFax", workFax);
                }
                // 住宅传真
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME) {
                    String homeFax = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    jsonObject.put("homeFax", homeFax);
                } // 寻呼机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_PAGER) {
                    String pager = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("pager", pager);
                }
                // 回拨号码
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK) {
                    String quickNum = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("quickNum", quickNum);
                }
                // 公司总机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN) {
                    String jobTel = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("jobTel", jobTel);
                }
                // 车载电话
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_CAR) {
                    String carNum = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("carNum", carNum);
                } // ISDN
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_ISDN) {
                    String isdn = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("isdn", isdn);
                } // 总机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MAIN) {
                    String tel = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("tel", tel);
                }
                // 无线装置
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_RADIO) {
                    String wirelessDev = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    jsonObject.put("wirelessDev", wirelessDev);
                } // 电报
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_TELEX) {
                    String telegram = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("telegram", telegram);
                }
                // TTY_TDD
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD) {
                    String tty_tdd = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("tty_tdd", tty_tdd);
                }
                // 单位手机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE) {
                    String jobMobile = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("jobMobile", jobMobile);
                }
                // 单位寻呼机
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER) {
                    String jobPager = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("jobPager", jobPager);
                } // 助理
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT) {
                    String assistantNum = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("assistantNum", assistantNum);
                } // 彩信
                if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MMS) {
                    String mms = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jsonObject.put("mms", mms);
                }

                String mobileEmail = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                jsonObject.put("mobileEmail", mobileEmail);
            }
        }
        // 查找event地址
        if (ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE.equals(mimetype)) { // 取出时间类型
            int eventType = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.TYPE)); // 生日
            if (eventType == ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY) {
                String birthday = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                jsonObject.put("birthday", birthday);
            }
            // 周年纪念日
            if (eventType == ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY) {
                String anniversary = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                jsonObject.put("anniversary", anniversary);
            }
        }
        // 获取即时通讯消息
        if (ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE.equals(mimetype)) { // 取出即时消息类型
            int protocal = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.PROTOCOL));
            if (ContactsContract.CommonDataKinds.Im.TYPE_CUSTOM == protocal) {
                String workMsg = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
                jsonObject.put("workMsg", workMsg);
            } else if (ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN == protocal) {
                String workMsg = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
                jsonObject.put("workMsg", workMsg);
            }
            if (ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ == protocal) {
                String instantsMsg = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));

                jsonObject.put("instantsMsg", instantsMsg);
            }
        }
        // 获取备注信息
        if (ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE.equals(mimetype)) {
            String remark = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
            jsonObject.put("remark", remark);
        }
        // 获取昵称信息
        if (ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE.equals(mimetype)) {
            String nickName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME));
            jsonObject.put("nickName", nickName);
        }
        // 获取组织信息
        if (ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE.equals(mimetype)) { // 取出组织类型
            int orgType = cursor.getInt(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Organization.TYPE)); // 单位
            if (orgType == ContactsContract.CommonDataKinds.Organization.TYPE_CUSTOM) { // if (orgType ==
                // Organization.TYPE_WORK)
                // {
                String company = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
                jsonObject.put("company", company);
                String jobTitle = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
                jsonObject.put("jobTitle", jobTitle);
                String department = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Organization.DEPARTMENT));
                jsonObject.put("department", department);
            }
        }
        // 获取网站信息
        if (ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE.equals(mimetype)) { // 取出组织类型
            int webType = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.TYPE)); // 主页
            if (webType == ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM) {

                String home = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Website.URL));
                jsonObject.put("home", home);
            } // 主页
            else if (webType == ContactsContract.CommonDataKinds.Website.TYPE_HOME) {
                String home = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Website.URL));
                jsonObject.put("home", home);
            }
            // 个人主页
            if (webType == ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE) {
                String homePage = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Website.URL));
                jsonObject.put("homePage", homePage);
            }
            // 工作主页
            if (webType == ContactsContract.CommonDataKinds.Website.TYPE_WORK) {
                String workPage = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Website.URL));
                jsonObject.put("workPage", workPage);
            }
        }
        // 查找通讯地址
        if (ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE.equals(mimetype)) { // 取出邮件类型
            int postalType = cursor.getInt(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE)); // 单位通讯地址
            if (postalType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK) {
                String street = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                jsonObject.put("street", street);
                String ciry = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                jsonObject.put("ciry", ciry);
                String box = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                jsonObject.put("box", box);
                String area = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD));
                jsonObject.put("area", area);

                String state = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                jsonObject.put("state", state);
                String zip = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                jsonObject.put("zip", zip);
                String country = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                jsonObject.put("country", country);
            }
            // 住宅通讯地址
            if (postalType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME) {
                String homeStreet = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                jsonObject.put("homeStreet", homeStreet);
                String homeCity = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                jsonObject.put("homeCity", homeCity);
                String homeBox = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                jsonObject.put("homeBox", homeBox);
                String homeArea = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD));
                jsonObject.put("homeArea", homeArea);
                String homeState = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                jsonObject.put("homeState", homeState);
                String homeZip = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                jsonObject.put("homeZip", homeZip);
                String homeCountry = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                jsonObject.put("homeCountry", homeCountry);
            }
            // 其他通讯地址
            if (postalType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER) {
                String otherStreet = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                jsonObject.put("otherStreet", otherStreet);

                String otherCity = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                jsonObject.put("otherCity", otherCity);
                String otherBox = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                jsonObject.put("otherBox", otherBox);
                String otherArea = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD));
                jsonObject.put("otherArea", otherArea);
                String otherState = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                jsonObject.put("otherState", otherState);
                String otherZip = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                jsonObject.put("otherZip", otherZip);
                String otherCountry = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                jsonObject.put("otherCountry", otherCountry);
            }
        }
        cursor.close();
        Log.i("contactData", contactData.toString());
        return contactData.toString();
    }


}
