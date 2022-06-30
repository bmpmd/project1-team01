/**
 * this script will get the id of the selected reimbursement 
 * to approve/deny and Send the request to the proper endpoint 
 * with the id of the reimbursement to approve/deny 
 */
//get buttons + document elements 
const approveButton = document.getElementById('approve-button');
const denyButton =document.getElementById('deny-button');

// function to get value of radio that is selected
//value of radio is set to the id of reimbursement
const getSelectedRadioHandler = (input) =>{
    //get radios 
    const radios = document.getElementsByName("select");
    

    //loop thru list of radios to get 
    //the checked one 
    for(i = 0; i < radios.length; i++){
        if(radios[i].checked){
            let reimbursementId = radios[i].getAttribute("id")
            if(input == "APPROVE")
                updateApprove(reimbursementId);
            else
                updateDeny(reimbursementId);
            //alert("You selected the element w id = " + reimbursementId);
            
        }

    }

} 


//function to update reimbursement w/ status APPROVE to /accept
const updateApprove = (input) =>  {
    //isntead of alreting, we send it as a param? 
    alert(`you have APPROVED the reimbursement with ID = ${input}`);
}


//function to update reimbursement w/ status DENY to /deny 
const updateDeny = (input) =>  {
    //isntead of alreting, we send it as a param? 
    alert(`you have DENIED the reimbursement with ID = ${input}`);
}

// add event listeners to send to appropriate action 

approveButton.addEventListener("click", (event) => getSelectedRadioHandler("APPROVE"));
denyButton.addEventListener("click", (event) => getSelectedRadioHandler("DENY"));
