# Design Document

## Overview
The Elder Scrolls: Ike (name pending) is a task-tracking software inspired by Habitica. Tasks are represented as quests, similarly to a Quest Log in an RPG. Quests may have attributes such as Timed (having a due date), Daily (regularly occurring on intervals), or Questline (part of a series of quests). Quests may also have a quest location or specific set rewards. 

The project is intended primarily for personal use, but if it is phenominally functional and people express interest, I may open the project to the public.

## Scenarios
Here are some potential use-case scenarios:

### Scenario 1
Ike is practicing their scales on the guitar as part of the "Learn The Major Scales" quest. This quest is in a long questline named "Learn The Guitar". Ike is working on the questline because the reward for the final quest is "Buy A New Guitar"!

### Scenario 2
Ike really wants to play The Legend of Zelda: Breath of the Wild, but unfortunately, today is Thursday, a day when Zelda is normally illegal. Ike consults their Quest Log, and sees that the quest "Work An Hour On The Elder Scrolls: Ike" has the reward "Play Zelda For A Half Hour". So, Ike dutifully puts in an hour of work to enjoy a half hour of play.

### Scenario 3
Ike struggles to keep plants alive when they're not in plain sight. So, Ike adds a new Daily Quest named "Water The Damn Plants," which becomes active once a week on Tuesday, and has the reward "Eat A Cookie".

## Object Specifications

A Quest object will have the following fields:
 - ``Long`` ID
 - ``String`` Quest Name
 - ``String`` Quest Description
 - ``Enumeration: Timed, Daily, Questline, Other`` Quest Type
 - ``Data Type?`` Location
    - String is easiest, could enumerate Home, Library, etc. or put use actual location data
 - ``List<QuestObjective>``Quest Objectives (nullable?)
 - ``String`` Quest Reward
 - ``Boolean`` Completed?
    - Alternatively, ``Enumeration: NotStarted, InProgress, Completed`` Quest Status?

 A Quest Objective object will have the following fiels:
 - ``String`` Objective Name
 - ``Boolean`` Completed?

A Questline object will have the following fields:
- ``Long`` ID
- ``String`` Questline Name
- ``List<Quest>`` Quest List
    - More likely, ``List<Long>`` Quest List (by ID)
- ``Long`` Active Quest ID
- ``Boolean`` Completed?
Functionality Note: Only Objectives in the Active Quest can be completed at a time, and only that Quest's Rewards can be received. Completing a Quest in a Questline unlocked the next Quest.

 I will also include standard repository and controller items, as well as unit tests.

## UI
- Layout similar to a quest log in a video game
    - Examples: [Skyrim](https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.megabearsfan.net%2Fimage.axd%2F2015%2F11%2FSkyrim-quest_log_filler_2.jpg&f=1&nofb=1&ipt=a935a9a28a0806c4ff3305dd12a8dcbc7f2f9a66a907dd70a5e85f4729acd3f3&ipo=images), [Zelda: Breath of the Wild](https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Foyster.ignimgs.com%2Fmediawiki%2Fapis.ign.com%2Fthe-legend-of-zelda-hd%2Fthumb%2F1%2F18%2FAdventure_Log_Screen.jpg%2F468px-Adventure_Log_Screen.jpg&f=1&nofb=1&ipt=4a082bbb04a70add3f7315e512c4e59c82a47216aad2ab7f83ded8ed4b78312a&ipo=images), [Horizon Zero Dawn](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.vox-cdn.com%2Fthumbor%2F4IwQX1wbQ_msEqyjQsJFjQaYZL0%3D%2F0x0%3A3840x2160%2F1200x0%2Ffilters%3Afocal(0x0%3A3840x2160)%3Ano_upscale()%2Fcdn.vox-cdn.com%2Fuploads%2Fchorus_asset%2Ffile%2F8149467%2FHorizon_Zero_Dawn__20170309234355.jpg&f=1&nofb=1&ipt=145998de67904b155b035f72ca07d2a97f09ffad8ee241da54a83f2e3fd7ed09&ipo=images) [(Categories)](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.vox-cdn.com%2Fuploads%2Fchorus_asset%2Ffile%2F8034933%2F0036_HZD.jpg&f=1&nofb=1&ipt=d6e18edd7ac17330324f679cefed3c6ff448b92e1f9dc52de39b70635486cc98&ipo=images), [Kim Keiser Design Template](https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.kimkiserdesign.com%2Fwp-content%2Fuploads%2F2014%2F07%2Fquestlog.jpg&f=1&nofb=1&ipt=de5947e2ee29400e6c72f3feb1f459e2ca3d205856b8cee632008a5d6ac0e44d&ipo=images)
- Quests represented in categories, split by questline, timed, daily, like in HZD
    - OR all layed out in a list, like in Skyrim
- Option to filter based on location, sort by due date

## Milestones
0. Project Setup
    1. Come up with a better name
    2. Set up Spring Boot
    3. Due: 6/23
1. Quest and Objective Management 
    1. Make basic database schema for Quests, Questline, Objectives
    2. Set up project
    3. Create Quest and Objective objects
    4. Create Repository and Controller objects
    5. Create CRUD operations
    6. Due: 6/29
2. Questline Progression
    1. Create Questline Object
    2. Create CRUD operations
    3. Due: 7/4
3. Filtering and Sorting
    1. Create methods to filter quest based on type, status, due date, location
    2. Implement sorting for quests based on due date
    3. Due 7/10
4. UI
    1. Design crude UI
    2. Build UI to fetch from generic database
    3. Fetch quests from backend
    4. Connect user interactions to CRUD methods
    5. Due 7/17

## Beyond MVP
If opening the app to other people:
- Refactor to include ownership
- Add and configure Spring security
- Add sign-in feature to UI

The following are cool ideas that would be fun to implement if I get this working and still have a brain:
- Randomly generated rewards from a drop pool
- Items and an inventory, where items are tickets to certain things
    - E.g. Complete a task, and receive a Ticket To Play Video Games, which can be redeemed in the future for that item
    - Known issue: Dealing with item images. Are items only text? If they have images, where do the images come from?
- Quest descriptions generated in RPG style using GPT-4 API
- Create API to connect to other services and streamline quest-interaction
    - Idea is to create and complete quest objectives automatically using bluetooth-connected technology (similar to how quests are created and completed automatically in video games)
- Connect to Google Maps API to have a visual map with pinpoints of quest objectives
    - Possibly make use of [Google Maps for SNES API](https://github.com/ciciplusplus/mapnes)
- 