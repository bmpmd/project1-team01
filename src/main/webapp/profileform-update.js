window.onload= function(){

    //when loaded populate fields 
    //aka, set attribute value = {field}

    document.getElementById("firstname").setAttribute("value", `${JSON.parse(sessionStorage.getItem("currentUser")).firstName}`)
    document.getElementById("lastname").setAttribute("value", `${JSON.parse(sessionStorage.getItem("currentUser")).lastName}`)
    document.getElementById("username").setAttribute("value", `${JSON.parse(sessionStorage.getItem("currentUser")).username}`)
    document.getElementById("password").setAttribute("value", `${JSON.parse(sessionStorage.getItem("currentUser")).pwd}`)
    document.getElementById("email").setAttribute("value", `${JSON.parse(sessionStorage.getItem("currentUser")).email}`)

}