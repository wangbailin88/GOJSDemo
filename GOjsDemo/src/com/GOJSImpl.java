package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class GOJSImpl {
	int i = 0;
	//声明每级节点的颜色
	String rootNodeColor = "#34495E";   //根节点颜色
	String qqNodeColor = "#F2C40F";  //qq节点颜色
	String idCardNodeColor = "#1BBC9B";  //身份证节点颜色
	String emailNodeColor = "#E77E23"; //邮箱节点颜色
	String addressNodeColor = "#3598DB"; //地址节点颜色
	String phoneNodeColor = "#9B58B5"; //电话节点颜色
	String weiboNodeColor = "#E77E23"; //微博节点颜色
	String contactsNodeColor = "#E94C3D";//联系人节点颜色
	String cellphoneNodeColor = "#9B58B5"; //手机节点颜色
	String telephoneNodeColor = "#0381FC"; //固话节点颜色
	String qqGradColor = "#F39C11";//qq群节点颜色
	
	
	//废弃
	String oneColor = "#A7C0DC";//
	String towColor = "#E867FA";
	String threeColor = "#0381FC";
	String fourColor = "#AF7483";
	public String jsonData(String filePath) throws Exception {
		i = 1;
		
		// TODO Auto-generated method stub
		String jsonContent = new JsonUtil().readJosnFile(filePath);
		
		jsonContent = jsonContent.replaceAll("\"", "\\\\\"");
		String str = "{\"result\":\"";
		String end = "\"}";
		jsonContent =str+ jsonContent;
		jsonContent = jsonContent + end;
		jsonContent = "["+jsonContent+"]";
//		if(!StringUtils.isNull(jsonContent)){
//			return jsonContent;
//		}
		System.out.println(jsonContent);
		JSONArray jsonArray = JSONArray.fromObject(jsonContent);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
			
		JSONArray jsonArrayResult = JSONArray.fromObject(jsonObject.get("result"));
		JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(0);
		
		JSONArray jsonArrayContact = JSONArray.fromObject(jsonObjectResult.get("Contact"));
		
		//return
		JSONArray jsonArray2 = new JSONArray();
		//root节点
		JSONObject rootNode = new JSONObject();
		rootNode.put("key", "root");
		rootNode.put("color", rootNodeColor);
		rootNode.put("image", "root.png");
		rootNode.put("name", "root");
		rootNode.put("value", "根节点");
		//qq节点
		JSONObject qqNode = new JSONObject();
		qqNode.put("key", i++);
		//qqNode.put("name", "QQ");
		//qqNode.put("value", "QQ节点");
		//qqNode.put("image", "qqNode.png");
		qqNode.put("name", "");
		qqNode.put("value", "");
		qqNode.put("image", "qqNode.png");
		qqNode.put("color", qqNodeColor);
		qqNode.put("dir", "left");
		qqNode.put("parent", rootNode.get("key"));
		qqGr(jsonArray2, jsonObjectResult, qqNode);
		
		//微博节点
		JSONObject weiboNode = new JSONObject();
		weiboNode.put("key", i++);
//		weiboNode.put("name", "微博");
//		weiboNode.put("image", "weiboNode.png");
//		weiboNode.put("value", "微博节点");
		
		
		weiboNode.put("name", "");
		weiboNode.put("image", "weiboNode.png");
		weiboNode.put("value", "");
		weiboNode.put("dir", "below");
		weiboNode.put("color", weiboNodeColor);
		weiboNode.put("dir", "right");
		weiboNode.put("parent", rootNode.get("key"));
		//weibo num
		weibo(jsonArray2, jsonObjectResult, weiboNode);
		//联系人节点
		JSONObject contactsNode = new JSONObject();
		contactsNode.put("key", i++);
		contactsNode.put("color", contactsNodeColor);
		contactsNode.put("dir", "right");
		//contactsNode.put("name", "联系人");
		//contactsNode.put("value", "联系人节点");
		//contactsNode.put("image", "contactsNode.png");
		contactsNode.put("name", "");
		contactsNode.put("value", "");
		contactsNode.put("image", "contactsNode.png");
		contactsNode.put("parent", rootNode.get("key"));
		//联系人号码
		contactsNum(jsonArray2, jsonArrayContact, contactsNode);
		
		
		//电话节点
		JSONObject phoneNode = new JSONObject();
		phoneNode.put("key", i++);
		phoneNode.put("color", phoneNodeColor);
		phoneNode.put("dir", "below");
//		phoneNode.put("name", "电话");
//		phoneNode.put("value", "电话节点");
//		phoneNode.put("image", "phoneNode.png");
		phoneNode.put("name", "");
		phoneNode.put("value", "");
		phoneNode.put("image", "phoneNode.png");
		phoneNode.put("parent", rootNode.get("key"));
		//固话节点
		JSONObject telephoneNode = new JSONObject();
		telephoneNode.put("key", i++);
		telephoneNode.put("color", telephoneNodeColor);
		telephoneNode.put("name", "固话");
		telephoneNode.put("value", "固话节点");
		telephoneNode.put("image", "telephoneNode.png");
		telephoneNode.put("parent", phoneNode.get("key"));
		//固话号码
		telephoneNum(jsonArray2, jsonObjectResult, telephoneNode);
		
		//手机号节点
		JSONObject cellphoneNode = new JSONObject();
		cellphoneNode.put("key", i++);
		cellphoneNode.put("color", cellphoneNodeColor);
		cellphoneNode.put("name", "手机");
		cellphoneNode.put("value", "手机节点");
		cellphoneNode.put("image", "cellphoneNode.png");
		cellphoneNode.put("parent", phoneNode.get("key"));
		//手机号码
		cellphoneNum(jsonArray2, jsonObjectResult, cellphoneNode);
		
		
		//地址节点
		JSONObject addressNode = new JSONObject();
		addressNode.put("key", i++);
		addressNode.put("dir", "top");
		addressNode.put("color", addressNodeColor);
		//addressNode.put("name", "地址");
		//addressNode.put("value", "地址节点");
		//addressNode.put("image", "addressNode.png");
		
		
		addressNode.put("name", "");
		addressNode.put("value", "");
		addressNode.put("image", "addressNode.png");
		addressNode.put("parent", rootNode.get("key"));
		//地址信息
		adressInfo(jsonArray2, jsonObjectResult, addressNode);
		
		
		//邮箱节点
		JSONObject emailNode = new JSONObject();
		emailNode.put("key", i++);
		emailNode.put("color", emailNodeColor);
		emailNode.put("dir", "top");
		//emailNode.put("name", "邮箱");
		//emailNode.put("value", "邮箱节点");
		//emailNode.put("image", "emailNode.png");
		emailNode.put("name", "");
		emailNode.put("value", "");
		emailNode.put("image", "emailNode.png");
		emailNode.put("parent", rootNode.get("key"));
		//邮箱号码
		emailNum(jsonArray2, jsonObjectResult, emailNode);
		
		
		
		//身份证节点
		JSONObject idCNode = new JSONObject();
		idCNode.put("key", i++);
		idCNode.put("color", idCardNodeColor);
		idCNode.put("dir", "right");
		//idCNode.put("name", "身份证");
		//idCNode.put("value", "身份证节点");
		//idCNode.put("image", "idCNode.png");
		idCNode.put("name", "");
		idCNode.put("value", "");
		idCNode.put("image", "idCNode.png");
		idCNode.put("parent", rootNode.get("key"));
		//身份证号
		IDCard(jsonArray2, jsonObjectResult, idCNode);
		
		jsonArray2.add(qqNode);
		jsonArray2.add(idCNode);
		jsonArray2.add(emailNode);
		jsonArray2.add(addressNode);
		jsonArray2.add(contactsNode);
		jsonArray2.add(phoneNode);
		jsonArray2.add(cellphoneNode);
		jsonArray2.add(telephoneNode);
		jsonArray2.add(weiboNode);
		jsonArray2.add(rootNode);
		
		
		
		JSONObject object = new JSONObject();
		object.put("class", "go.TreeModel");
		object.put("nodeDataArray", jsonArray2);
		return object.toString();
	}
	//qq群号
	public void qqGr(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject qqNode){
		//qq群数据
				JSONArray jsonArrayQQGroup = JSONArray.fromObject(jsonObjectResult.get("Qqgroup"));
				//得到qq号
				Set<String> qqSet = new HashSet<String>();
				for (int j = 0; j < jsonArrayQQGroup.size(); j++) {
					JSONObject object = JSONObject.fromObject(jsonArrayQQGroup.get(j));
					qqSet.add(object.get("qqnum").toString());
				}
				for (String s : qqSet) {
					//qq号
					JSONObject qqNum = new JSONObject();
					qqNum.put("key", i++);
					qqNum.put("name", "QQ号");
					qqNum.put("value", s);
					qqNum.put("color", qqNodeColor);
					qqNum.put("image", "qqNum.png");
					qqNum.put("parent", qqNode.get("key"));
					jsonArray2.add(qqNum);
					for (int j = 0; j < jsonArrayQQGroup.size(); j++) {
						JSONObject object = JSONObject.fromObject(jsonArrayQQGroup.get(j));
						
						//取得群号
						String qqgrNum = object.get("qqnum").toString();
						
						//判断qq号和qq群主号是否一致
						if(s.equals(qqgrNum)){
							//qq群数据
							JSONObject qqgr = new JSONObject();
							qqgr.put("key", i++);
							qqgr.put("name", "QQ群号");
							qqgr.put("color", qqGradColor);
							qqgr.put("value", object.get("qunnum").toString());
							qqgr.put("parent", qqNum.get("key"));
							qqgr.put("image", "qqgr.png");
							jsonArray2.add(qqgr);
						}
					}
				}
	}
	//联系人号码
	public void contactsNum(JSONArray jsonArray2,JSONArray jsonArrayContact,JSONObject contactsNode){
		for (int j = 0; j<jsonArrayContact.size();j++) {
			JSONObject jsonObjectContact = jsonArrayContact.getJSONObject(j);
			JSONObject contactsNum = new JSONObject();
			contactsNum.put("key", i++);
			contactsNum.put("name", "联系人号码");
			
			contactsNum.put("color", contactsNodeColor);
			contactsNum.put("value", jsonObjectContact.get("own_phone"));
			contactsNum.put("parent", contactsNode.get("key"));
			contactsNum.put("image", "contactsNum.png");
			jsonArray2.add(contactsNum);
		}
	}
	//邮箱地址
	public void emailNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject emailNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("mail".equals(jsonObjectPro.get("type"))){
				JSONObject emailNum = new JSONObject();
				emailNum.put("key", i++);
				emailNum.put("name", "邮箱号码");
				emailNum.put("color", emailNodeColor);
				emailNum.put("value", jsonObjectPro.get("encodeValue"));
				emailNum.put("parent", emailNode.get("key"));
				emailNum.put("image", "emailNum.png");
				jsonArray2.add(emailNum);
			}
				
		}
	}
	//身份证号
	public void IDCard(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject idCNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("id".equals(jsonObjectPro.get("type"))){
				//身份证号
				String idCard = "";
				try {
					 idCard = jsonObjectPro.getString("encodeValue").toString();
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}
				//判断身份证是否符合正则表达式
				if(idCardCheck(idCard)){
					JSONObject idCardNum = new JSONObject();
					idCardNum.put("key", i++);
					idCardNum.put("name", "身份证号");
					idCardNum.put("color", idCardNodeColor);
					idCardNum.put("value", idCard);
					idCardNum.put("parent", idCNode.get("key"));
					idCardNum.put("image", "idCardNum.png");
					jsonArray2.add(idCardNum);
					
					//出生日期及性别
//					Map<String, String> map = getdateOfBirthAndSex(idCard);
//					JSONObject dateOfBirthInfo = new JSONObject();
//					dateOfBirthInfo.put("key", i++);
//					dateOfBirthInfo.put("name", "出生日期");
//					dateOfBirthInfo.put("color", idCardNodeColor);
//					dateOfBirthInfo.put("value", map.get("dateOfBirth"));
//					dateOfBirthInfo.put("parent", idCardNum.get("key"));
//					dateOfBirthInfo.put("image", "dateOfBirth.png");
//					jsonArray2.add(dateOfBirthInfo);
					//性别
//					JSONObject sexInfo = new JSONObject();
//					sexInfo.put("key", i++);
//					sexInfo.put("name", "性别");
//					sexInfo.put("color", idCardNodeColor);
//					sexInfo.put("value", map.get("sex"));
//					sexInfo.put("parent", idCardNum.get("key"));
//					sexInfo.put("image", "sex.png");
//					jsonArray2.add(sexInfo);
				}
			}
				
		}
	}
	
	//地址信息
	public void adressInfo(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject addressNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			JSONArray jsonArrayAtt = JSONArray.fromObject(jsonObjectPro.get("attribute"));
			for (int j = 0; j < jsonArrayAtt.size(); j++) {
				String address = "";
				try {
					address = jsonArrayAtt.getJSONObject(j).get("addr").toString();
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}
				//判断地址值是否为空，如果为空将不加入到jsonarray数组中
				if(!StringUtils.isNull(address)){
					JSONObject adressInfo = new JSONObject();
					adressInfo.put("key", i++);
					adressInfo.put("name", "地址");
					adressInfo.put("color", addressNodeColor);
					adressInfo.put("value", jsonArrayAtt.getJSONObject(j).get("addr"));
					adressInfo.put("parent", addressNode.get("key"));
					adressInfo.put("image", "adressInfo.png");
					jsonArray2.add(adressInfo);
				}
				
			}
		}
	}
	//object.put("cell", jsonArrayAtt.getJSONObject(j).get("cell"));
	//object.put("phone", jsonArrayAtt.getJSONObject(j).get("phone"));
	//固话号码
	public void telephoneNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject telephoneNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("phone".equals(jsonObjectPro.get("type"))){
				JSONObject telephoneNum = new JSONObject();
				telephoneNum.put("key", i++);
				telephoneNum.put("name", "固话号码");
				telephoneNum.put("color", telephoneNodeColor);
				telephoneNum.put("value", jsonObjectPro.get("encodeValue"));
				telephoneNum.put("parent", telephoneNode.get("key"));
				telephoneNum.put("image", "telephoneNum.png");
				jsonArray2.add(telephoneNum);
			}
				
		}
	}
	//手机号号码
	public void cellphoneNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject cellphoneNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("cell".equals(jsonObjectPro.get("type"))){
				JSONObject cellphoneNum = new JSONObject();
				cellphoneNum.put("key", i++);
				cellphoneNum.put("color", cellphoneNodeColor);
				cellphoneNum.put("name", "手机号码");
				cellphoneNum.put("value", jsonObjectPro.get("encodeValue"));
				cellphoneNum.put("parent", cellphoneNode.get("key"));
				cellphoneNum.put("image", "cellphoneNum.png");
				jsonArray2.add(cellphoneNum);
			}
				
		}
	}
	//weibo num
		public void weibo(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject weiboNode){
			JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
			for (int n = 0; n < jsonArrayPro.size(); n++) {
				JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
				if("weibo".equals(jsonObjectPro.get("type"))){
					JSONObject weiboNum = new JSONObject();
					weiboNum.put("key", i++);  
					weiboNum.put("color", weiboNodeColor);
					weiboNum.put("name", "weibo");
					weiboNum.put("value", jsonObjectPro.get("encodeValue"));
					weiboNum.put("parent", weiboNode.get("key"));
					weiboNum.put("image", "weiboNode.png");
					jsonArray2.add(weiboNum);
				}
					
			}
		}
	//判断身份证：要么是15位，要么是18位， 
	public boolean idCardCheck(String idCard){
		//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）  
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
        //通过Pattern获得Matcher  
        Matcher idNumMatcher = idNumPattern.matcher(idCard);  
        //判断用户输入是否为身份证号      如果不是返回null
        if(idNumMatcher.matches()){  
                return true;
        } 
       
		return false;
	}
	
	//get 出生日期及性别
	public Map<String, String> getdateOfBirthAndSex(String idCard){
		System.out.println("您的出生年月日是：");  
        //如果是，定义正则表达式提取出身份证中的出生日期  
        Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日  
        //通过Pattern获得Matcher  
        Matcher birthDateMather= birthDatePattern.matcher(idCard);  
        //通过Matcher获得用户的出生年月日  
        if(birthDateMather.find()){  
            String year = birthDateMather.group(1);  
            String month = birthDateMather.group(2);  
            String date = birthDateMather.group(3);
            //输出用户的出生年月日  
            String dateOfBirth = year+"年"+month+"月"+date+"日";
            String sex = idCard.substring(16,17);
            if(Integer.parseInt(sex)%2==0){
            	sex = "女";
            }else{
            	sex = "男";
            }
            Map<String, String> map = new HashMap<>();
            map.put("dateOfBirth", dateOfBirth);
            map.put("sex", sex);
            return map;
        }
        return null;
	}
	public static void main(String[] args) throws Exception {  
    	//new PersonInfofetchTaskServiceImpl().jsonData("D://data/personInfofetch/20161024/test.json");
    	//new PersonInfofetchTaskServiceImpl().jsonData("D://data/personInfofetch/20161024/40288aac57db6d300157db6df7c70003.json");
    	String jsonContent = new JsonUtil().readJosnFile("D://data/personInfofetch/20161024/test.json");
		
		jsonContent = jsonContent.replaceAll("\"", "\\\\\"");
		String str = "{\"result\":\"";
		String end = "\"}";
		jsonContent =str+ jsonContent;
		jsonContent = jsonContent + end;
		jsonContent = "["+jsonContent+"]";
		
		JSONArray jsonArray = JSONArray.fromObject(jsonContent);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		//JSONObject jsonObject = jsonArray.get(0);
		JSONArray array = JSONArray.fromObject(jsonObject.get("result"));
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.getJSONObject(i));
		}
    } 
}
