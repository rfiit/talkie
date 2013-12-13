function getAjaxResponse(str, handler) {
    var ajax = new XMLHttpRequest();

    ajax.onreadystatechange=function() {
        if (ajax.readyState==4 && ajax.status==200) {
            //document.getElementById(divelementid).innerHTML=ajax.responseText;
            handler(ajax.responseText);
            }
        }
    ajax.open("GET",str,true);
    ajax.send();
}

function askQuestion(question) {
    //alert("q:"+question);
    getAjaxResponse("request?request="+question,
    function(ask){
        //alert(ask);
        document.getElementById("lastquestions").style.display="block";
        document.getElementById("qmess").innerHTML = question;
        document.getElementById("amess").innerHTML = ask;
    });

    getAjaxResponse("viewlist.jsp",
        function(ask){
            //alert(ask);
            document.getElementById("viewlistdiv").innerHTML = ask;
        });
}
