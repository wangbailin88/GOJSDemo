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
	//����ÿ���ڵ����ɫ
	String rootNodeColor = "#34495E";   //���ڵ���ɫ
	String qqNodeColor = "#F2C40F";  //qq�ڵ���ɫ
	String idCardNodeColor = "#1BBC9B";  //���֤�ڵ���ɫ
	String emailNodeColor = "#E77E23"; //����ڵ���ɫ
	String addressNodeColor = "#3598DB"; //��ַ�ڵ���ɫ
	String phoneNodeColor = "#9B58B5"; //�绰�ڵ���ɫ
	String weiboNodeColor = "#E77E23"; //΢���ڵ���ɫ
	String contactsNodeColor = "#E94C3D";//��ϵ�˽ڵ���ɫ
	String cellphoneNodeColor = "#9B58B5"; //�ֻ��ڵ���ɫ
	String telephoneNodeColor = "#0381FC"; //�̻��ڵ���ɫ
	String qqGradColor = "#F39C11";//qqȺ�ڵ���ɫ
	
	
	//����
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
		//root�ڵ�
		JSONObject rootNode = new JSONObject();
		rootNode.put("key", "root");
		rootNode.put("color", rootNodeColor);
		rootNode.put("image", "root.png");
		rootNode.put("name", "root");
		rootNode.put("value", "���ڵ�");
		//qq�ڵ�
		JSONObject qqNode = new JSONObject();
		qqNode.put("key", i++);
		//qqNode.put("name", "QQ");
		//qqNode.put("value", "QQ�ڵ�");
		//qqNode.put("image", "qqNode.png");
		qqNode.put("name", "");
		qqNode.put("value", "");
		qqNode.put("image", "qqNode.png");
		qqNode.put("color", qqNodeColor);
		qqNode.put("dir", "left");
		qqNode.put("parent", rootNode.get("key"));
		qqGr(jsonArray2, jsonObjectResult, qqNode);
		
		//΢���ڵ�
		JSONObject weiboNode = new JSONObject();
		weiboNode.put("key", i++);
//		weiboNode.put("name", "΢��");
//		weiboNode.put("image", "weiboNode.png");
//		weiboNode.put("value", "΢���ڵ�");
		
		
		weiboNode.put("name", "");
		weiboNode.put("image", "weiboNode.png");
		weiboNode.put("value", "");
		weiboNode.put("dir", "below");
		weiboNode.put("color", weiboNodeColor);
		weiboNode.put("dir", "right");
		weiboNode.put("parent", rootNode.get("key"));
		//weibo num
		weibo(jsonArray2, jsonObjectResult, weiboNode);
		//��ϵ�˽ڵ�
		JSONObject contactsNode = new JSONObject();
		contactsNode.put("key", i++);
		contactsNode.put("color", contactsNodeColor);
		contactsNode.put("dir", "right");
		//contactsNode.put("name", "��ϵ��");
		//contactsNode.put("value", "��ϵ�˽ڵ�");
		//contactsNode.put("image", "contactsNode.png");
		contactsNode.put("name", "");
		contactsNode.put("value", "");
		contactsNode.put("image", "contactsNode.png");
		contactsNode.put("parent", rootNode.get("key"));
		//��ϵ�˺���
		contactsNum(jsonArray2, jsonArrayContact, contactsNode);
		
		
		//�绰�ڵ�
		JSONObject phoneNode = new JSONObject();
		phoneNode.put("key", i++);
		phoneNode.put("color", phoneNodeColor);
		phoneNode.put("dir", "below");
//		phoneNode.put("name", "�绰");
//		phoneNode.put("value", "�绰�ڵ�");
//		phoneNode.put("image", "phoneNode.png");
		phoneNode.put("name", "");
		phoneNode.put("value", "");
		phoneNode.put("image", "phoneNode.png");
		phoneNode.put("parent", rootNode.get("key"));
		//�̻��ڵ�
		JSONObject telephoneNode = new JSONObject();
		telephoneNode.put("key", i++);
		telephoneNode.put("color", telephoneNodeColor);
		telephoneNode.put("name", "�̻�");
		telephoneNode.put("value", "�̻��ڵ�");
		telephoneNode.put("image", "telephoneNode.png");
		telephoneNode.put("parent", phoneNode.get("key"));
		//�̻�����
		telephoneNum(jsonArray2, jsonObjectResult, telephoneNode);
		
		//�ֻ��Žڵ�
		JSONObject cellphoneNode = new JSONObject();
		cellphoneNode.put("key", i++);
		cellphoneNode.put("color", cellphoneNodeColor);
		cellphoneNode.put("name", "�ֻ�");
		cellphoneNode.put("value", "�ֻ��ڵ�");
		cellphoneNode.put("image", "cellphoneNode.png");
		cellphoneNode.put("parent", phoneNode.get("key"));
		//�ֻ�����
		cellphoneNum(jsonArray2, jsonObjectResult, cellphoneNode);
		
		
		//��ַ�ڵ�
		JSONObject addressNode = new JSONObject();
		addressNode.put("key", i++);
		addressNode.put("dir", "top");
		addressNode.put("color", addressNodeColor);
		//addressNode.put("name", "��ַ");
		//addressNode.put("value", "��ַ�ڵ�");
		//addressNode.put("image", "addressNode.png");
		
		
		addressNode.put("name", "");
		addressNode.put("value", "");
		addressNode.put("image", "addressNode.png");
		addressNode.put("parent", rootNode.get("key"));
		//��ַ��Ϣ
		adressInfo(jsonArray2, jsonObjectResult, addressNode);
		
		
		//����ڵ�
		JSONObject emailNode = new JSONObject();
		emailNode.put("key", i++);
		emailNode.put("color", emailNodeColor);
		emailNode.put("dir", "top");
		//emailNode.put("name", "����");
		//emailNode.put("value", "����ڵ�");
		//emailNode.put("image", "emailNode.png");
		emailNode.put("name", "");
		emailNode.put("value", "");
		emailNode.put("image", "emailNode.png");
		emailNode.put("parent", rootNode.get("key"));
		//�������
		emailNum(jsonArray2, jsonObjectResult, emailNode);
		
		
		
		//���֤�ڵ�
		JSONObject idCNode = new JSONObject();
		idCNode.put("key", i++);
		idCNode.put("color", idCardNodeColor);
		idCNode.put("dir", "right");
		//idCNode.put("name", "���֤");
		//idCNode.put("value", "���֤�ڵ�");
		//idCNode.put("image", "idCNode.png");
		idCNode.put("name", "");
		idCNode.put("value", "");
		idCNode.put("image", "idCNode.png");
		idCNode.put("parent", rootNode.get("key"));
		//���֤��
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
	//qqȺ��
	public void qqGr(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject qqNode){
		//qqȺ����
				JSONArray jsonArrayQQGroup = JSONArray.fromObject(jsonObjectResult.get("Qqgroup"));
				//�õ�qq��
				Set<String> qqSet = new HashSet<String>();
				for (int j = 0; j < jsonArrayQQGroup.size(); j++) {
					JSONObject object = JSONObject.fromObject(jsonArrayQQGroup.get(j));
					qqSet.add(object.get("qqnum").toString());
				}
				for (String s : qqSet) {
					//qq��
					JSONObject qqNum = new JSONObject();
					qqNum.put("key", i++);
					qqNum.put("name", "QQ��");
					qqNum.put("value", s);
					qqNum.put("color", qqNodeColor);
					qqNum.put("image", "qqNum.png");
					qqNum.put("parent", qqNode.get("key"));
					jsonArray2.add(qqNum);
					for (int j = 0; j < jsonArrayQQGroup.size(); j++) {
						JSONObject object = JSONObject.fromObject(jsonArrayQQGroup.get(j));
						
						//ȡ��Ⱥ��
						String qqgrNum = object.get("qqnum").toString();
						
						//�ж�qq�ź�qqȺ�����Ƿ�һ��
						if(s.equals(qqgrNum)){
							//qqȺ����
							JSONObject qqgr = new JSONObject();
							qqgr.put("key", i++);
							qqgr.put("name", "QQȺ��");
							qqgr.put("color", qqGradColor);
							qqgr.put("value", object.get("qunnum").toString());
							qqgr.put("parent", qqNum.get("key"));
							qqgr.put("image", "qqgr.png");
							jsonArray2.add(qqgr);
						}
					}
				}
	}
	//��ϵ�˺���
	public void contactsNum(JSONArray jsonArray2,JSONArray jsonArrayContact,JSONObject contactsNode){
		for (int j = 0; j<jsonArrayContact.size();j++) {
			JSONObject jsonObjectContact = jsonArrayContact.getJSONObject(j);
			JSONObject contactsNum = new JSONObject();
			contactsNum.put("key", i++);
			contactsNum.put("name", "��ϵ�˺���");
			
			contactsNum.put("color", contactsNodeColor);
			contactsNum.put("value", jsonObjectContact.get("own_phone"));
			contactsNum.put("parent", contactsNode.get("key"));
			contactsNum.put("image", "contactsNum.png");
			jsonArray2.add(contactsNum);
		}
	}
	//�����ַ
	public void emailNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject emailNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("mail".equals(jsonObjectPro.get("type"))){
				JSONObject emailNum = new JSONObject();
				emailNum.put("key", i++);
				emailNum.put("name", "�������");
				emailNum.put("color", emailNodeColor);
				emailNum.put("value", jsonObjectPro.get("encodeValue"));
				emailNum.put("parent", emailNode.get("key"));
				emailNum.put("image", "emailNum.png");
				jsonArray2.add(emailNum);
			}
				
		}
	}
	//���֤��
	public void IDCard(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject idCNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("id".equals(jsonObjectPro.get("type"))){
				//���֤��
				String idCard = "";
				try {
					 idCard = jsonObjectPro.getString("encodeValue").toString();
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}
				//�ж����֤�Ƿ����������ʽ
				if(idCardCheck(idCard)){
					JSONObject idCardNum = new JSONObject();
					idCardNum.put("key", i++);
					idCardNum.put("name", "���֤��");
					idCardNum.put("color", idCardNodeColor);
					idCardNum.put("value", idCard);
					idCardNum.put("parent", idCNode.get("key"));
					idCardNum.put("image", "idCardNum.png");
					jsonArray2.add(idCardNum);
					
					//�������ڼ��Ա�
//					Map<String, String> map = getdateOfBirthAndSex(idCard);
//					JSONObject dateOfBirthInfo = new JSONObject();
//					dateOfBirthInfo.put("key", i++);
//					dateOfBirthInfo.put("name", "��������");
//					dateOfBirthInfo.put("color", idCardNodeColor);
//					dateOfBirthInfo.put("value", map.get("dateOfBirth"));
//					dateOfBirthInfo.put("parent", idCardNum.get("key"));
//					dateOfBirthInfo.put("image", "dateOfBirth.png");
//					jsonArray2.add(dateOfBirthInfo);
					//�Ա�
//					JSONObject sexInfo = new JSONObject();
//					sexInfo.put("key", i++);
//					sexInfo.put("name", "�Ա�");
//					sexInfo.put("color", idCardNodeColor);
//					sexInfo.put("value", map.get("sex"));
//					sexInfo.put("parent", idCardNum.get("key"));
//					sexInfo.put("image", "sex.png");
//					jsonArray2.add(sexInfo);
				}
			}
				
		}
	}
	
	//��ַ��Ϣ
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
				//�жϵ�ֵַ�Ƿ�Ϊ�գ����Ϊ�ս������뵽jsonarray������
				if(!StringUtils.isNull(address)){
					JSONObject adressInfo = new JSONObject();
					adressInfo.put("key", i++);
					adressInfo.put("name", "��ַ");
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
	//�̻�����
	public void telephoneNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject telephoneNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("phone".equals(jsonObjectPro.get("type"))){
				JSONObject telephoneNum = new JSONObject();
				telephoneNum.put("key", i++);
				telephoneNum.put("name", "�̻�����");
				telephoneNum.put("color", telephoneNodeColor);
				telephoneNum.put("value", jsonObjectPro.get("encodeValue"));
				telephoneNum.put("parent", telephoneNode.get("key"));
				telephoneNum.put("image", "telephoneNum.png");
				jsonArray2.add(telephoneNum);
			}
				
		}
	}
	//�ֻ��ź���
	public void cellphoneNum(JSONArray jsonArray2,JSONObject jsonObjectResult,JSONObject cellphoneNode){
		JSONArray jsonArrayPro = JSONArray.fromObject(jsonObjectResult.get("Properties"));
		for (int n = 0; n < jsonArrayPro.size(); n++) {
			JSONObject jsonObjectPro = jsonArrayPro.getJSONObject(n);
			if("cell".equals(jsonObjectPro.get("type"))){
				JSONObject cellphoneNum = new JSONObject();
				cellphoneNum.put("key", i++);
				cellphoneNum.put("color", cellphoneNodeColor);
				cellphoneNum.put("name", "�ֻ�����");
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
	//�ж����֤��Ҫô��15λ��Ҫô��18λ�� 
	public boolean idCardCheck(String idCard){
		//�����б��û����֤�ŵ�������ʽ��Ҫô��15λ��Ҫô��18λ�����һλ����Ϊ��ĸ��  
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
        //ͨ��Pattern���Matcher  
        Matcher idNumMatcher = idNumPattern.matcher(idCard);  
        //�ж��û������Ƿ�Ϊ���֤��      ������Ƿ���null
        if(idNumMatcher.matches()){  
                return true;
        } 
       
		return false;
	}
	
	//get �������ڼ��Ա�
	public Map<String, String> getdateOfBirthAndSex(String idCard){
		System.out.println("���ĳ����������ǣ�");  
        //����ǣ�����������ʽ��ȡ�����֤�еĳ�������  
        Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//���֤�ϵ�ǰ6λ�Լ�����������  
        //ͨ��Pattern���Matcher  
        Matcher birthDateMather= birthDatePattern.matcher(idCard);  
        //ͨ��Matcher����û��ĳ���������  
        if(birthDateMather.find()){  
            String year = birthDateMather.group(1);  
            String month = birthDateMather.group(2);  
            String date = birthDateMather.group(3);
            //����û��ĳ���������  
            String dateOfBirth = year+"��"+month+"��"+date+"��";
            String sex = idCard.substring(16,17);
            if(Integer.parseInt(sex)%2==0){
            	sex = "Ů";
            }else{
            	sex = "��";
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
