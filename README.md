# My_ModelViewPresenter_AndroidExample
My way to understand MVP architecture pattern in Android development. I want to escape from activity lifecycle trap...

For long time I was trying to understand MVP pattern in Android development. 
There are such different cases and ways on the Internet, so I decided to come up my MVP pattern example.

Maybe I have not enough experience in programming and this code can't be MVP realisation. 
But I've just tried to cheat activity lifecycle system. I didn't want to depend on Activity lifecycle.
For example, when we have screen orientation changes, our Activity will be created again and all data in interface will be lost.
Actually we can use Bundle object "savedInstanceState", but we can't put everything into Bundle. 
So, the best solution is making new class like Presenter, that will have listeners for all important events in Activity.
And Activity can call Presenter to get some data. 
Main statement for this architecture is: One certain Presenter for one certain Activity!

So, Presenter is like Activity, but without any troubles like recreating after screen orientation changes.
