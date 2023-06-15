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
 - ID
 - Quest Name
 - Quest Description
 - Quest Objectives (nullable?)
 - Quest Reward

 A Questline object will be a list of Quests, with one Quest marked as "Active." Only Objectives in that Quest can be completed at a time, and only that Quest's Rewards can be received. Completing a Quest in a Questline unlocked the next Quest.

## UI
- Quests represented in categories, split by questline, daily
- Option to filter based on location, sort by due date
- Should do research on RPG Quest Logs for inspiration

## Milestones
The dates on these milestones will be set once I have finished learning Spring Boot
- Learn Spring Boot
- Create Quest object
- Created database to hold quest object, with associated CRUD methods
- Create methods to Get quests with filters
- Create simple UI to interact with Quests

## Beyond MVP
The following are cool ideas that would be fun to implement if I get this working and still have a brain:
- Randomly generated rewards from a drop pool
- Quest descriptions generated in RPG style using GPT-4 API
- Create API to connect to other services and streamline quest-interaction
    - Idea is to create and complete quest objectives 