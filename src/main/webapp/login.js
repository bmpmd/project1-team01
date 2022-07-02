document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('login-button')
        .addEventListener('click', signin);
});

const signin = (ev) =>{
    //stop page from reloading 
    ev.preventDefault();

    let username = document.getElementById("username");
    let password = document.getElementById("password")

    console.log(username);
    console.log(password);

    fetch('http://${hostname}/project01-team01/login-js', {
        //send this json to the api endpoint 
        //this endpoint has yet to be made, but will do confirm login 

        method: 'POST',
        // redirect: 'follow',
        // credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })

    //then when we get the response back from the endpoint, we see if the response is ok 
    //if it's not ok,  say there's been an err        
    }).then(function (response){


        if(!response.ok){
            throw Error("ERROR has occured.")
        }

        let childDiv = document.getElementById("warningText");
        childDiv.innerHTML = `<p style="color:red;"><b>Failed to sign in!</b> <br> <b>Username or Password is incorrect</b></p>`;

        return response.json();


    }).then(function(data){
        console.log(data);
        console.log(data.status);

        if(data.id > 0){

            //console log 
            console.log("success!!");

            //set the curr user to the json obj 
            //JSON STRINGIFY the json object to save the current user 
            //WHENEVER we want the user back, we JSON.pasrse() the string if we want it back 
            /**
             * ie. 
             *  var user = {'name':'John'};
                sessionStorage.setItem('user', JSON.stringify(user));
                var obj = JSON.parse(sessionStorage.user);
             */
            sessionStorage.setItem("currentUser", JSON.stringify(data));

            //and finally, redirect to the page 
            //window.location.href = 'http://localhost:8080/movie-app/index.html'
        }else if(data.status == "process failed"){
            console.log('login failed...');
            let childDiv = document.getElementById("warningText");
            childDiv.innerHTML = `<p style="color:red;"><b>Failed to sign in!</b> <br> <b>Username or Password is incorrect</b></p>`;

        }
    }).catch(error => {
        console.log(error);
        // warning message later...
        let childDiv= document.getElementById("warningText")
        childDiv.innerHTML =`<p style="color:red;"><b>Failed to sign in!</b> <br> <b>Username or Password is incorrect</b></p>`;
    })




    




}
