/**
 * this script will get the id of the selected reimbursement 
 * to approve/deny and Send the request to the proper endpoint 
 * with the id of the reimbursement to approve/deny 
 */
//get buttons + document elements 
const approveButton = document.getElementById('approve-button');
const denyButton = document.getElementById('deny-button');

// function to get value of radio that is selected
//value of radio is set to the id of reimbursement
const getSelectedRadioHandler = (input) => {
    //get radios 
    const radios = document.getElementsByName("select");


    //loop thru list of radios to get 
    //the checked one 
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            let reimbursementId = radios[i].getAttribute("id")
            if (input == "APPROVE")
                updateApprove(reimbursementId);
            else
                updateDeny(reimbursementId);
        }

    }

}


//function to update reimbursement w/ status APPROVE to proper endpoint
const updateApprove = (input) => {
    //sending a json to the api endpoint 
    console.log(`you have APPROVED the reimbursement with ID = ${input}`);
    let str = sessionStorage.getAttribute("currentUser");
    let managerId = JSON.parse(str);

    fetch(`http://${hostname}/project1-team01/approve-reimburse`, {
        //send this json to the api endpoint 
        //this endpoint has yet to be made, but will do approve reimburse 

        method: 'POST',
        // redirect: 'follow',
        // credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: managerId,
            reimburseId: input
        })

        //then when we get the response back from the endpoint, we see if the response is ok 
        //if it's not ok,  say there's been an err        
    }).then(response => {
        if (!response.ok) {
            throw Error("ERROR has occurred.")
        }

        // redirect to error page here 
        window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`

        return response.json();




    }).then(data => {
        console.log(data);
        console.log(data.status);

        //if status ok 
        if (data.status == "process completed") {
            //console log 
            console.log(data);

            //give feedback to user saying that op was success
            //redirect user to page saying op was success here 
            window.location.href = `http://${hostname}/project1-team01/success-page-manager.html`

        }
        else if (data.status == "process failed") {
            //if failed, tell user 
            console.log("updating reimbursement failed..");
            //redirect user to page saying op was failed here 
            window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`

        }
    }).catch(error => {
        console.log(error)
        // insert error page here 
        window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`


    })





}


//function to update reimbursement w/ status DENY to /deny 
const updateDeny = (input) => {
    //isntead of alreting, we send it as a param? 
    console.log(`you have DENIED the reimbursement with ID = ${input}`);
    //sending a json to the api endpoint 
    let str = sessionStorage.getAttribute("currentUser");
    let managerId = JSON.parse(str);

    fetch(`http://${hostname}/project1-team01/deny-reimburse`, {
        //send this json to the api endpoint 
        //this endpoint has yet to be made, but will do approve reimburse 

        method: 'POST',
        // redirect: 'follow',
        // credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: managerId,
            reimburseId: input
        })

        //then when we get the response back from the endpoint, we see if the response is ok 
        //if it's not ok,  say there's been an err        
    }).then(response => {
        if (!response.ok) {
            throw Error("ERROR has occurred.")
        }

        // redirect to error page here 
        window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`

        return response.json();




    }).then(data => {
        console.log(data);
        console.log(data.status);

        //if status ok 
        if (data.status == "process completed") {
            //console log 
            console.log(data);

            //give feedback to user saying that op was success
            //redirect user to page saying op was success here 
            window.location.href = `http://${hostname}/project1-team01/success-page-manager.html`

        }
        else if (data.status == "process failed") {
            //if failed, tell user 
            console.log("updating reimbursement failed..");
            //redirect user to page saying op was failed here 
            window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`

        }
    }).catch(error => {
        console.log(error)
        // insert error page here 
        window.location.href = `http://${hostname}/project1-team01/fail-page-manager.html`


    })

}

// add event listeners to send to appropriate action 
approveButton.addEventListener("click", (event) => getSelectedRadioHandler("APPROVE"));
denyButton.addEventListener("click", (event) => getSelectedRadioHandler("DENY"));
