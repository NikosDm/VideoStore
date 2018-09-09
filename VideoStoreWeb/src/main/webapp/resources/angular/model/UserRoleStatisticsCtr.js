function UserRoleStatisticsCtr(data){
    var rolesStatistics = $.map(data, function(roleStat) {
        return { label: roleStat.roleName, y: roleStat.totalUsers };
    });
    var options = {
        animationEnabled: true,
        title: {
            text: "Users Statistics"
        },
        data: [{
            type: "doughnut",
            innerRadius: "40%",
            showInLegend: true,
            legendText: "{label}",
            indexLabel: "{label}: #percent%",
            dataPoints: rolesStatistics
        }]
    };
    return options;
}
