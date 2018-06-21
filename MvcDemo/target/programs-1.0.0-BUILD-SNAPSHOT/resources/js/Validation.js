function ValidationTextname(){
	var result=document.getElementById("nameId");
	if(result.value=="")
		{
		alert("Please Enter valid name eg.[Abc]");
		return false;
		}
	var result1=document.getElementById("emailId");
	if(result1.value=="")
		{
		alert("Please Enter valid email ID eg.[abc@gmail.com]");
		return false;
		}
	var passWord=document.getElementById("passwordId");
	  if((passWord.value=="") && (passWord.lenght>5))
	  {
	    alert("Please Enter at least 6 digit Password");
	    return false;
	  } 
	  var mobileNo=document.getElementById("mobileNo");
	  if((mobileNo.value=="") && (mobileNo.lenght==10))
	  {
	    alert("Please valid mobile No eg.[9879696954]");
	    return false;
	  } 
	  return true;
}