# alan_robot
Bu android uygulaması Alan AI Apı kullanarak listeye eleman ekleme, silme, güncelleme ve google sayfasını açma, arama ekranını açma, hava durumu sayfasını açma  özelliklerini içeren bir akıllı uygulamadır. Listedeki veriler room database içinde tutulmaktadır. Bu uygulamayı daha çok Alan AI API yı anlamak için geliştirdim. settings.gradle da güncelleme yaparak API'nın çalışmasını sağladım. Alan AI studio kodumu da buraya ekledim. Proje Id kısmını sildim kendinizin oluşturması gerekir. 
 ### Uygulamada Kullanılan Özellikler:
  <br>Name | Version |</br>
   Kotlin | 1.7.20 | 
  <br>Alan AI SDK | 4.12.0 |</br>
  Room  | 2.5.1 | 
  
  
# Project Display Video <p> 



https://github.com/oykuyildirim/alan_robot/assets/37236494/e12cd9a3-9618-44f4-a938-022ad88e184c


# Alan AI STUDIO Code <p>


intent('Add list $(my_item* (.+))', p => {
    p.play('I will add');
    p.play({command: 'add_list', item:p.my_item.value})
});

intent('Remove $(my_item* (.+)) from list ', p => {
    p.play('I will remove');
    p.play({command: 'remove_list', item:p.my_item.value})
});

intent('Update $(my_item* (.+)) to $(upd_item* (.+)) ', p => {
    p.play('Updating item');
    p.play({command: 'update_item', item:p.my_item.value, updated:p.upd_item.value })
});

intent('open google', p => {
    p.play('I will go google');
    p.play({command: 'open_google'})
});

intent('weather forecast', p => {
    p.play('I will go wheater forecast page');
    p.play({command: 'open_weather'})
});

intent('call', p => {
    p.play('opening calling screen');
    p.play({command: 'call'})
});

// Give your AI assistant some knowledge about the world
corpus(`
    Hello, I'm Alan.
    This is a demo application.
    You can learn how to teach Alan useful skills.
    I can teach you how to write Alan Scripts.
    I can help you. I can do a lot of things. I can answer questions. I can do tasks.
    But they should be relevant to this application.
    I can help with this application.
    I'm Alan. I'm a virtual assistant. I'm here to help you with applications.
    This is a demo script. It shows how to use Alan.
    You can create dialogs and teach me.
    For example: I can help navigate this application.
`);





