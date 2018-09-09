function MediaStatisticsCtr(data) {
    var options = {
        animationEnabled: true,
        title: {
            text: "Media Statistics"
        },
        data: [{
            type: "doughnut",
            innerRadius: "40%",
            showInLegend: true,
            legendText: "{label}",
            indexLabel: "{label}: #percent%",
            dataPoints: [
                { label: "Video Games", y: data.videoGamesTotal },
                { label: "Music Disks", y: data.musicDisksTotal },
                { label: "Movies", y: data.moviesTotal }
            ]
        }]
    };
    return options;
}
