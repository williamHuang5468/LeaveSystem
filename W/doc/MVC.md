# [Question : MVC on the command line](http://stackoverflow.com/questions/1904115/mvc-on-the-command-line)

I am looking into the MVC from a Command line point of view (not web and no framework).. nice and simple. the only thing that confuses me is the View part of this? (well it may not be the only but its the main one)

from the IBM site the view has the following definition

The view provides the presentation of the model. It is the look of the application. The view can access the model getters, but it has no knowledge of the setters. In addition, it knows nothing about the controller. The view should be notified when changes to the model occur.

So if i have the following:

- Model
  - Person
- View
  - EditPersonDetails
- Controller
  - PersonController

My person controller could pass a person object to the EditPeronDetails view, however how does my view edit the person if it cannot access its setters, how can i edit the person?

Also does the view class only do one thing? ie can I have a Person View, which has methods for Create, View, Delete, edit the person

## Answer

Is it really necessary to apply a 3 letter acronym here? if you must:

1. user sees the console screen, which shows the view (lines of texts)
1. user types something on command line, which is an even from view
1. controller gets the event, i.e. the user input
1. controller does something accordingly, and updates the model (persons)
1. controller tells the view to repaint, i.e. print some more lines.

psuedocode:

```Python
Person // model
   update();

View
   modelUpdated()
       println(Person.name); ...

Controller
     main()
         while( input = getInput() )
             person.update(...);
             view.modelUpdated();
```
