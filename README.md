# SuperHero

Usage - 

Get an access token from https://superheroapi.com/
Place token at "superhero_api_key" at the "local.properties" file

local.properties exmaple:
  sdk.dir=C\:\\Users\\guy\\AppData\\Local\\Android\\Sdk
  superhero_api_key=123456789
  


Architectural choices - 

  - I decided to store the recommendations in a Room database.
    The reason was that those SuperHeroes are always the same so no need to keep retrieving them.

    I decided not to store the search results in the database because I thought it would lead to an incorrectly performing application - 
      
      For example, if the user searches the API for "batman", he would get several results, which then needs to be correctly stored in the database, 
      so that further search of the same query "batman" would retrieve the same results,
      which is not a problem (as you may see at the "database_addition branch" under com.example.android.superhero.database.SuperHeroDao.getSuperHeroes()).
      The actual problem is if at that point the user searches for "bat", querying the DB would give us the previous "batman" results
      but the API would retrieve much more results. 
     
    For that behavioral reason, I found caching the search results quite impossible to correctly implement.
    
    
  - I decided to use the recommended MVVM architecture, including Navigation for screen navigation.
  - Repository layer used when the ViewModel needs to retrieve further data.
    This layer mediates between the ViewModel and the Networking/Database layer.
