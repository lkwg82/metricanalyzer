var id = 'div#metricHint';
$(document).ready(function(){    
    $("#myTable").tablesorter(); 
    $('td:not(.col-0)').bind('mouseover', function(){
        $(this).parent().find('td').css({
            'background-color': '#ff0000'
        })
        $('.' + $(this).attr('class')).css('background-color', '#ff0000');
        
        var max = $('th').length;
        var index = $('tr td').index(this);
        var i = index % max;
            //alert($('th')index(index).text());
        $(id).text($('th:eq('+i+')').text()  );
        
        
        
        $(id).css('top', $(this).offset().top - 30);
        $(id).css('left', $(this).offset().left + 50);
        
    }).bind('mouseout', function(){
        $(this).parent().find('td').css({
            'background-color': '#fff'
        });
        $('.' + $(this).attr('class')).css('background-color', '#fff');
        $(id).text('leer');
    });
    
    $('td:not(.col-0)').click(function(){
    
        var link = $(this).parent().find('td:first').find('a').attr('href');
        var dir = $(id).attr('name');
        if ( link === undefined )
        {
            return;
        
        }else{
        // link = ;
        link = link.replace(/.*\//, dir + '/');
        
        var iframe = '<iframe id="inlinesource" src="' + link + '"></iframe>';
        
        var _id = 'div#inlinesource';
        var fid = 'iframe#inlinesource';
        $(_id).html(iframe);
        $(_id).prepend("<h2/>");
        $(_id).find('h2').prepend(link.replace(/\%23/, '#').replace(/\.html$/,'').replace(/^.*\//,''));
        
        // expand div
        var width = $('table').outerWidth() - 20;
        var max_width = $(document).width();
        $(_id).css('width', max_width - width);
        
        var left = $('table').position().left;
        $(_id).css('left', (left + width + 20) + "px");
        
        $(_id).css('height', $(document).height() + "px");
        
        // stretch iframe to the dimensions of the div
        $(_id).find(fid).css('height', $(_id).height() + 'px');
        $(_id).find(fid).css('width', $(_id).width() + 'px');}
    });
    
});
