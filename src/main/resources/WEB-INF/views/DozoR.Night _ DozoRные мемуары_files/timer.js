function countDown(tick)
    {
    if (tick == 00)
        {
    	document.getElementById('clock').innerHTML = "00:00";
	    document.getElementById('clock').style.color = 'ff0000';
        }

    var time = "";
    var minute = Math.floor(tick / 60);
    if (minute < 10)
        {
        time += "0";
        }
    time += minute + ":";
    var second = tick % 60;
    if (second < 10)
        {
        time += "0";
        }
    time += second;
  	if (tick > -1 ) {
	    document.getElementById('clock').innerHTML = time;
	} 
    --tick;
    var command = "countDown(" + tick + ")";
    window.setTimeout(command,1000);
    }
