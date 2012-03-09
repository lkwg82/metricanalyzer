$(document).ready(function(){

    $('td:not(.col-0)').bind('mouseover', function(){
        $(this).parent().find('td').css({
            'background-color': '#ff0000'
        });
        $('.' + $(this).attr('class')).css('background-color', '#ff0000');
    }).bind('mouseout', function(){
        $(this).parent().find('td').css({
            'background-color': '#fff'
        });
        $('.' + $(this).attr('class')).css('background-color', '#fff');
    });
    
});

