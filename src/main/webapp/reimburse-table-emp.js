

//this js will show all PENDING reimbursements 
//shows all data cols except author and resolver for now 

let table  = document.querySelector('table');
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
    th1.innerHTML = 'ID';

    //amount
    let th2 = document.createElement('th')
    th2.innerHTML = 'Amount';

    //description
    let th3 = document.createElement('th')
    th3.innerHTML = 'Description';


    //time submitted
    let th5 = document.createElement('th')
    th5.innerHTML = 'Date Submitted';

    //status 
    let th6 = document.createElement('th')
    th6.innerHTML = 'Status';

    //type
    let th7 = document.createElement('th')
    th7.innerHTML = 'Type';

    //append the child nodes to the header 
    headerRow.appendChild(th0);
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
   // headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);

    //for each element in datra, console log it 
    data.forEach(element => {
        console.log(element);

        let row = document.createElement('tr'); // tr = table row 
        let td0 = document.createElement("td");//select 
        let td1 = document.createElement('td');//table data 1 
        let td2 = document.createElement('td');//table data 2
        let td3 = document.createElement('td');//table data 3 etc..
       // let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');
        
        let year = element.submitted.year;
        let month = element.submitted.monthValue;
        let day = element.submitted.dayOfMonth;
        let hr = element.submitted.hour;
        let min = element.submitted.minute;
        
        
        //set inner html of eachc ell to the diff properties of a user (first name, last, user)
        //this is dependent on the json string's elements!! TODO HERE
        row.id = element.id;
        td0.innerHTML = `<input class="form-check-input" type="radio" name="select" id="${element.id}" value="${element.id}">`
        td1.innerHTML = element.id;
        td2.innerHTML = element.amount; 
        td3.innerHTML = element.description;
      //  td4.innerHTML = element.dateResolved;
        td5.innerHTML = `${month}/${day}/${year}`
        td6.innerHTML = element.status;
        td7.innerHTML = element.type;

        //finally, append each table call to the row 
        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        //row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        

        //append row to the table 
        table.appendChild(row);

    });




}

//when page  is loaded execute this anon function
window.onload = function(){
    console.log("fetching json of reimbursements for current employee ....")

    //fetch API is a modern interface that allows you 
    // to make HTTP requests to a server 
    // and process the resiults you get back asynchronously 

    let hostname = window.location.host // tyhis will grab IP of where it's deployed (localhost, or Live server )
    //this is the url that retrieves the employee list with a template literal
    //EDIT: we must remove the portname because when it's deployed it wont need this bc port is already inferred when deploying
    
    //THIS IS THE LINE where we want to fetch the table. the end url will change depending on which
    let id = JSON.parse(sessionStorage.getItem('currentUser')).id; 
    fetch(`http://${hostname}/project1-team01/reimbursements-id`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				id: id
			})
		})
    .then(response => response.json() ) // takes a json string 
                                        //and trnasforms it to Javascript object
    //.then(obj => console.log(obj));//then print       
      .then(buildTable);//automatically passes the data that's been parsed (the js object)                         

}


/**
 * 
 * 
 * 
 * 
 *  <!-- head-->
            <thead>
              <tr>
                <!-- for radio-->
                <th scope="col">Select</th>
                <th scope="col">ID</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
              </tr> 
            </thead>
            <!-- body -->
            <tbody>
              <tr id="1">
                <td>
                  <input class="form-check-input" type="radio" name="select" id="1" value="1">
                </td>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>

              </tr>
              <tr id="2">
                <td>
                  <input class="form-check-input" type="radio" name="select" id="2" value="2">
                </td>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@yes</td>
              </tr>
              <tr id="3">
                <td>
                  <input class="form-check-input" type="radio" name="select" id="3" value="3">
                </td>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
              </tr>
            </tbody>
 */