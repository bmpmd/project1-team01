

//this js will show all reimbursements 
//shows all data cols except author and resolver for now 

let table  = document.querySelector("table")
//this save the table element to the variable 


function buildTable(data){
    //console log json str to make sure it's 
    console.log('build table method has been triggered');
    console.log(data);

    let header = document.createElement('thead'); // t head element: HTML elemet 

    let headerRow = document.createElement('tr');// tr is an HTML element 

    header.appendChild(headerRow);

    //we need to append the header(which appended the header row) to the table  we created above 
    table.appendChild(header);


     //col for making a selection 
     let th0 = document.createElement("th");
     th0.innerHTML = "Select"

    //create hearder col for each data column in db 
    //create a header column for ID
    let th1 = document.createElement('th')
    th1.innerHTML = 'Employee ID';

    //firt name 
    let th2 = document.createElement('th')
    th2.innerHTML = 'First Name';

    //lastname
    let th3 = document.createElement('th')
    th3.innerHTML = 'Last Name';


    //user
    let th4 = document.createElement('th')
    th4.innerHTML = 'Username';

    //email
    let th5 = document.createElement('th')
    th5.innerHTML = 'Email';

    

    //append the child nodes to the header 
    headerRow.appendChild(th0);
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
  

    //for each element in datra, console log it 
    data.forEach(element => {
        console.log(element);

        let row = document.createElement('tr'); // tr = table row 
        let td0 = document.createElement('td');
        let td1 = document.createElement('td');//table data 1 
        let td2 = document.createElement('td');//table data 2
        let td3 = document.createElement('td');//table data 3 etc..
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
    
        //set inner html of eachc ell to the diff properties of a user (first name, last, user)
        //this is dependent on the json string's elements!! TODO HERE
        row.id = element.id;
        td0.innerHTML = `<input class="form-check-input" type="radio" name="select" id="${element.id}" value="${element.id}">`
        td1.innerHTML = element.id;
        td2.innerHTML = element.firstName; 
        td3.innerHTML = element.lastName;
        td4.innerHTML = element.username;
        td5.innerHTML = element.email;
        

        //finally, append each table call to the row 
        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        
        

        //append row to the table 
        table.appendChild(row);

    });




}


//when page  is loaded execute this anon function
window.onload = function(){
    console.log("fetching json for all employees....")
   // table.innerHTML = "edit table"
    //fetch API is a modern interface that allows you 
    // to make HTTP requests to a server 
    // and process the resiults you get back asynchronously 

    let hostname = window.location.host // tyhis will grab IP of where it's deployed (localhost, or Live server )
    //this is the url that retrieves the employee list with a template literal
    //EDIT: we must remove the portname because when it's deployed it wont need this bc port is already inferred when deploying
    
    //THIS IS THE LINE where we want to fetch the table. the end url will change depending on which 
    fetch(`http://${hostname}/project1-team01/employees`) 
    .then(response => response.json())                                      
    .then(buildTable);//automatically passes the data that's been parsed (the js object)                         

}
