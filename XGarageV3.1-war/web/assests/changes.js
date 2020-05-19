/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function userForm(){
    document.getElementById('register-user').style.display="none";
     document.getElementById('register-park').style.display="block";
     document.getElementById('image-user').style.display="none";
      document.getElementById('image-park').style.display="block";
}


function parkForm (){
    document.getElementById('register-user').style.display="block";
    
     document.getElementById('register-park').style.display="none";
    
    document.getElementById('image-user').style.display="block";
    
      document.getElementById('image-park').style.display="none";
}