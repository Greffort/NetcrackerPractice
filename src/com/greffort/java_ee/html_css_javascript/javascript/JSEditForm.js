function scoreGame() {
    var score = decodeURIComponent(location.search.substr(1)).split('=');
    score.splice(0, 1);
    document.getElementById("textEName").value = score[0];
}
