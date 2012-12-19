jQuery.noConflict();
jQuery( 
    function() {
        jQuery( ".summary_report_sortable" ).each( 
            function() {
                // Move the first tr in tbody into thead instead
                // and change any <td> to <th>
                jQuery(this).prepend("<thead></thead>");
                jQuery( "thead", jQuery(this) ).append( jQuery( "tbody tr", this )[0] );
                jQuery("thead td", jQuery(this) ).each(
                    function() {
                        jQuery(this).replaceWith(jQuery('<th/>').html(jQuery(this).html()));
                    });
                jQuery(this).tablesorter();
	    } );
    } );

