




//when page is loaded, show curr user info 
window.onload = function(){

    console.log("getting profile information...")
    let user = JSON.parse(sessionStorage.getItem("currentUser"));

    let id = document.getElementById("id");
    let name = document.getElementById("name");
    
    let username = document.getElementById("username");
    let email = document.getElementById("email");

    id.innerHTML = `Employee ID: ${user.id}`;
    name.innerHTML = `Full Name: ${user.firstName} ${user.lastName}`;
    username.innerHTML = `Username: ${user.username}`;
    email.innerHTML = `Email: ${user.email}`;
    



}