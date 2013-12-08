function getAjaxResponse(str, handler) {
    var ajax = new XMLHttpRequest();

    ajax.onreadystatechange=function() {
        if (ajax.readyState==4 && ajax.status==200) {
            //document.getElementById(divelementid).innerHTML=ajax.responseText;
            handler(ajax.responseText);
            }
        }
    ajax.open("GET",document.location+"?type=ajax&request="+str,true);
    ajax.send();
}
