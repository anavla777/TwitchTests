# Automation project for Twitch.tv 
<p align="center"> 
<a href="https://twitch.tv"><img src="media/logo/twitch.svg" width="100" height="100"  alt="Twitch"/></a>
</p>

 > Twitch is a video live-streaming service that focuses on video game live streaming,
> including broadcasts of esports competitions, in addition to offering music broadcasts, creative content, and "in real life" streams.
## **Table of contents:**
____

* <a href="#tools">Technology stack</a>

* <a href="#cases">Examples of automated tests</a>

* <a href="#jenkins">Build in Jenkins</a>

* <a href="#console">Launch from command line</a>

* <a href="#allure">Allure report</a>

* <a href="#telegram"> Telegram Notifications</a>

* <a href="#video">Examples of test execution in Selenoid</a>
____
<a id="tools"></a>
## <a name="Technonlogy stack">**Technology stack**</a>

<p align="center">  
<a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>   
<a href="https://github.com/"><img src="media/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="media/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="media/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://github.com/allure-framework/allure2"><img src="media/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="media/logo/Allure2.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>   
</p>

____
<a id="cases"></a>
## <a name="Examples of automated tests">**Examples of automated tests:**</a>
____
- ✓ *twitchMainPageShouldDisplayCorrectTextTest() - Localization switch test*
- ✓ *categoryPageShouldDisplayCorrectTextTest() - Check text for selected category*
- ✓ *streamerShouldHaveCorrectNicknameAndVideoPlayerTest() - Streamer name and videoplayer test*
- ✓ *filterByTagsTest() - Test category filtration by tags*
- ✓ *openLiveChannelTest() - Check first LIVE stream and corresponing attributes*

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="media/logo/Jenkins.svg" width="25"/></a><a name="Building"></a>Building in [Jenkins](https://jenkins.autotests.cloud/job/C27-anavla777-unit14-TwitchTests/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/C27-anavla777-unit14-TwitchTests/"><img src="media/screenshots/JenkinsBuild.PNG" alt="Jenkins" width="950"/></a>  
</p>


### **Build parameters in Jenkins:**

- *browser (browser, firefox - by default)*
- *browserVersion (browser version, default - 123)*
- *browserSize (browser size, default - 1920x1080)*
- *baseUrl (url address)*
- *wdhost (Selenoid remote host)*

<a id="console"></a>
## Launch commands
___
***Locally:***
```bash  
gradle clean twitchTests 
"-Dbrowser=$BROWSER"
"-DbrowserVersion=$BROWSER_VERSION"
"-DbrowserSize=$BROWSER_SIZE"
"-Dwdhost=YOUR_REMOTE_SELENOID_HOST"
"-Dselenide.remote=https://USERNAME:PASS@wdhost/wd/hub"
```
- Specify your own parameters
- Launch allure reporting server:
```bash 
gradle allureServe
```
***Remote launch via Jenkins:***
```bash  
clean twitchTests
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbaseUrl=${baseUrl}
-Dselenide.remote=https://username:pass@${REMOTE_BROWSER_HOST}/wd/hub
```
where username and pass - specified credentials
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="media/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [report](https://jenkins.autotests.cloud/job/C27-anavla777-unit14-TwitchTests/allure/)</a>
___

### *Report main page*

<p align="center">  
<img title="Allure Overview Dashboard" src="media/screenshots/AllureMain.PNG" width="850" alt="">  
</p>  

### *Graphs*

  <p align="center">  
<img title="Allure Graphics" src="media/screenshots/Graphs.PNG" width="850" alt="">
</p>

___

<a id="telegram"></a>
## <img alt="Allure" height="25" src="media/logo/Telegram.svg" width="25"/></a> Telegram Notifications
____
<p align="center">  
<img title="Allure Overview Dashboard" src="media/screenshots/telegram.PNG" width="550" alt="">  
</p>

____
<a id="video"></a>
## <img alt="Selenoid" height="25" src="media/logo/Selenoid.svg" width="25"/></a> Example of test execution in Selenoid
____
<p align="center">
<img title="Selenoid Video" src="media/video/Selenoid.gif" width="550" height="350"  alt="video">   
</p>
