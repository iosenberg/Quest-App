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

## Non-goals
- Recreate Habitica
    - Specifically, I do not intend to implement any of the other "gamified" features of Habitica, such as characters and items. I want a much simpler concept and UI.

## Object Specifications

A Quest object will have the following fields:
 - ``Long`` ID
 - ``String`` Quest Name
 - ``String`` Quest Description
 - ``List<QuestObjective>``Quest Objectives (nullable?)
 - ``String`` Quest Reward
 - ``Boolean`` Completed?

 A Quest Objective object will have the following fiels:
 - ``String`` Objective Name
 - ``Boolean`` Completed?

A Questline object will have the following fields:
- ``List<Quest>`` Quest List
- ``Integer`` Active Quest (points to which quest is currently active)
- ``Boolean`` Completed?
Functionality Note: Only Objectives in the Active Quest can be completed at a time, and only that Quest's Rewards can be received. Completing a Quest in a Questline unlocked the next Quest.

 I will also include standard repository and controller items, as well as unit tests.

## UI
- Quests represented in categories, split by questline, daily
- Option to filter based on location, sort by due date
- Should do research on RPG Quest Logs for inspiration

## Milestones
The dates on these milestones will be set once I have finished learning Spring Boot
- Create Quest Objective and Quest objects
- Created database to hold quest object, with associated CRUD methods
- Create methods to Get quests with filters
- Create Questline object
- Update repository and API
- Create simple UI to display quests
- Add post and get functionality to UI

If opening the app to other people:
- Refactor to include ownership
- Add and configure Spring security
- Add sign-in feature to UI

## Beyond MVP
The following are cool ideas that would be fun to implement if I get this working and still have a brain:
- Randomly generated rewards from a drop pool
- Quest descriptions generated in RPG style using GPT-4 API
- Create API to connect to other services and streamline quest-interaction
    - Idea is to create and complete quest objectives automatically using bluetooth-connected technology (similar to how quests are created and completed automatically in video games)
- Connect to Google Maps API to have a visual map with pinpoints of quest objectives
    - Possibly make use of [Google Maps for SNES API](https://github.com/ciciplusplus/mapnes)