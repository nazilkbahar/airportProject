$(document).ready(function() {

	
	$('#searchButton').click(function() {
		
		var q = $("#inputValue").val();
		var data = {"q": q};
		$.ajax({	
			type:"GET",
			url:'/airports/search',
			data : data,        //html deki inputu
			
			success:function(donen_veri){
				//$("#veriBasacaginYer").append("<div> " + donen_veri[0].name + "</div>");	
						 //$('#table').html(donen_veri);    //verileri tableye gonder
				
				$("tr:has(td)").remove();   //tablo varsa kaldir
				$(donen_veri).each(function(index,item){
					//console.log(item);
					
					 $('#airportTable tbody').append(
		                        '<tr><td>' + item.id+
		                        '</td><td>' + item.name+
		                        '</td><td>' + item.city +
		                        '</td><td>' + item.country + 
		                        '</td><td>' + item.code +
		                        '</td><td>' + item.latitude +
		                        '</td><td>' + item.longitude +
		                        '</td></tr>'
		                    )
					
				});
						
			},
			error:function()
			{
				alert('error');
			}
				
		});
	});
	
	$('#airportTable tr').hover(function() {    //airportTable satiri uzzerindeyse
	    $(this).addClass('selected');         //satir uzerine geldiginde bir class ekle (selected sdinda)
	}, function() {
	    $(this).removeClass('selected');     //uzerinde degilse selected class ini kaldir
	});
	
	
	//for delete
	$('.deleteButton').click(function() {              
		if($('#airportTable tr').hasClass('selected')){    //hasClass sayesinde ayni class degerine sahip tum degerlere ayni isi yaptirabilirsin
			var selectedId = $(this).attr('data-button')  //html deki delete butonunun icinde tanimli data.
			//var deleteSatir = $('#airportTable tr');
			console.log(selectedId);
			var clicklenen_delete_butonu = $(this);
			$.ajax ({			
				url:'/airports/'+selectedId,
				type: 'DELETE',
				
				success:function(donen_veri){
				  
				 clicklenen_delete_butonu.parent().parent().remove();     //arayuzde silinen satiri direk kaldirmak icin
				 // location.reload();                  //arayuz sayfasini guncelliyor
				 
				},
				error: function() {
                   alert('silinmedi')					
				}
				
			})
		}	
	})
	
	//for edit(put)
	$('.airport-name').click(function() {
		var updateName=prompt("name ?",$(this).text());
		
		var updateId=$(this).attr('data-update')
		          //prompt icine yazacagin kisim
		var clicklenen_guncelle_satiri = $(this);     //yenilemeden gosterim icin bi tanim
		
		if(updateName){
		
			$.ajax({
				url:'/airports/'+updateId,
			    type:'PUT',
			    contentType: "application/json",
			    dataType: "json",
			    data:JSON.stringify({"name": updateName}),
			    success: function(donen_veri) {
				    	
			    		console.log(donen_veri);
			    		clicklenen_guncelle_satiri.text(updateName);  //arayuzde sayfayi yenilemeden updateyi gosterir
			    		// location.reload();  
			    		
			    },
			    error: function() {
				alert('fail update')
			    }    	
			    
		})
			
		}
		else {
			alert('Name alanı bos olamaz!')
		}

		
	
		
	})
	

	
	
	
	
	
});





