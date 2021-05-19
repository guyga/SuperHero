# SuperHero

Usage - 

Get an access token from https://superheroapi.com/
Place token at "superhero_api_key" at the "local.properties" file

local.properties exmaple:
  sdk.dir=C\:\\Users\\guy\\AppData\\Local\\Android\\Sdk
  superhero_api_key=123456789
  


Architectural choices - 
    
  - I decided to use the recommended MVVM architecture, including Navigation for screen navigation.
  - For passing data to the UI I have mostly used ViewBinding and DataBinding, to improve effiecency and im most cases remove the unnecessary fragment's involvement.
  - Repository layer used when the ViewModel needs to retrieve further data.
    This layer mediates between the ViewModel and the Networking/Database layer.
    
  - I decided to store the recommended SuperHeroes in a Room database.
    The reason was that those SuperHeroes are always the same so no need to keep retrieving them.

  - I decided not to store the other SuperHeroes (from the search results) in the database because I thought it would lead to an incorrectly performing application - 
      
      For example, if the user searches the API for "batman", he would get several results, which then needs to be correctly stored in the database, 
      so that a later search of the same query "batman" would retrieve the same results,
      [which is not a problem (as you may see at the "database_addition branch" under com.example.android.superhero.database.SuperHeroDao.getSuperHeroes()).]
      The actual problem is if at that point the user searches for "bat", querying the DB would give us the previous "batman" results
      but the API would retrieve much more results. But we can not possibly know that the DB results are not all the results and thus would present only partial search results. 
     
    For that behavioral reason, I found that caching the search results is quite impossible.
  
  
  - Because of the above point, i did struggle a little with the requirement to cache the images with a 1 day expiration.
      
      If i could simply store all the SuperHeroes in the database, i would simply add a timestamp field of when the image is loaded,
      so i can check it for expiration when loading.
      But since I found that I could not store the SuperHeroes while maintaining a correctly working application, as described above,
      this task became a bit harder - as i must store something to be able to know when a url is expired.
      That's why i decided to store to the database a url&timestamp object, so everytime i need to load a url, i can check the db for the timestamp for if its expired or not.
      The actual invalidation of that image is done through Glide.signature API - passing as the signature a different timestamp value causing a cache invalidation.
      This was tested by launching a local Server with a local image, 
      when its time to expire an image i would replace that image and make sure it bahaves as required (or simply observe that image loading animation).
      
      * Perhaps i didnt correctly understand this expiration requirement or the above point regarding the application behavior with search results,
      because this implementation does seem wierd, but it's working the way i understood the task.





      
