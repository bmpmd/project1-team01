/**
 * this script will get the id of the selected reimbursement 
 * to approve/deny and Send the request to the proper endpoint 
 * with the id of the reimbursement to approve/deny 
 */
//get buttons + document elements 
const viewButton = document.getElementById('view-button');

// function to get value of radio that is selected
//value of radio is set to the id of reimbursement
const getSelectedRadioHandler = () =>{
    //get radios 
    const radios = document.getElementsByName("select");
    

    //loop thru list of radios to get 
    //the checked one 
    for(i = 0; i < radios.length; i++){
        if(radios[i].checked){
            let reimbursementId = radios[i].getAttribute("id")
            viewEmployee(reimbursementId);
        }

    }

} 


//function to update reimbursement w/ status APPROVE to /accept
const viewEmployee = (input) =>  {
    //isntead of alreting, we send it as a param? 
    alert(`you have selected to VIEW the reimbursements of employee w/ ID =  ${input}`);
}



// add event listeners to send to appropriate action 

viewButton.addEventListener("click", getSelectedRadioHandler);
