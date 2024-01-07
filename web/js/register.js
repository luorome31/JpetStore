function checkUsername(username) {
  if (username===null||username===''||username.length===0){
    return '用户名不能为空';
  }

  return '';
}
$(function(){
      $('#username').on('blur',function () {
      var username =$(this).val();
      var validateMsg=checkUsername(username);
      if(validateMsg===''){
        $('#feedback').text('');
        $.ajax({
          type:'GET',
          url :'http://localhost:8080/JpetStore/usernameExist?username='+username,
          success:function (data) {
           if(data.result===true){
             $('#feedback').text('用户名已被使用');
           }else{
               $('#feedback').text('用户名可使用');
           }
          },
          error:function (errorMsg) {
          console.log(errorMsg);
          //     $('#feedback').text('用户名已存在');
          }
        })
      }else {
        $('#feedback').text(validateMsg);
      }
      });
});