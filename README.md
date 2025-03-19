# Overview #
Food Recommender is an app that recommends food to users based on the filters provided. This app aims to answer the question: What am I going to eat today?
Users can decide whether to eat out or stay home after choosing a type of food or cuisine. 
If the user chooses to eat out, the app will recommend restaurants in the area that match the user's selections. Right now, all restaurants are hard-coded into a Room database. 
If the user chooses to stay home, the app will recommend recipes that match the user's selections. The recipes are gathered from an API called TheMealDB. Each recipe has instructions displayed. More implementations include an ingredient list and a website that links to the recipe. 
The main missing implementations are a restaurant API, an account page, and a recommendation algorithm that listens to the user's food selection. 

# Figma Design #
![Eat Out](https://github.com/user-attachments/assets/7be5a09c-1570-45ad-b53f-7aa47831345a)
![Stay Home](https://github.com/user-attachments/assets/442944ee-e662-4495-86f3-1cc16187524e)
![Account](https://github.com/user-attachments/assets/56cfb429-3e7c-4ba5-a286-58e4ff76710a)
![Main](https://github.com/user-attachments/assets/20c000aa-58b4-4b59-8c6a-2701a3d78370)

# API #
https://www.themealdb.com/api.php

# Dependencies #
- coil 2.4.0
- converter gson 2.9.0
- retrofit 2.9.0
- room 2.6.1


