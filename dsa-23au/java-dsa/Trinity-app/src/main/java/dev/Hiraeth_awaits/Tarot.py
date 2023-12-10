import random

#Define the Tarot Deck
tarot_deck = [
    {"name": "The Fool", "meaning": "Naive, New Beginnings, Living in the Now, Innocence, Spontaneous"},
    {"name": "The Magician", "meaning": "Power, Divine Connection, Skill, Talent, Initiative"},
    {"name": "The High Priestess", "meaning": "Deep Knowledge, Intuition, Foresight, Wisdom, Understanding"},
    {"name": "The Empress", "meaning": "Feminine Power, Mother, Fertility, Success, Initiative"},
    {"name": "The Emperor", "meaning": "Male Power, Authority, Leadership, Proficiency, Wealth"},
    {"name": "The Heirophant", "meaning": "Tradition, Convention, Ritual, Religion, Endurance"},
    {"name": "The Lovers", "meaning": "Love, Attraction, Harmony, Union, Triumph Over Trials"},
    {"name": "The Chariot", "meaning": "Journey, Obstacle, Competition, Discipline, Victory"},
    {"name": "Justice", "meaning": "Balance, Equilibrium, Fairness, Neutrality, Moderation"},
    {"name": "The Hermit", "meaning": "A Sage, Vigilant, Withdrawl, Discretion, Contemplation"},
    {"name": "The Wheel of Fortune", "meaning": "A New Cycle, Destiny, Advancement, Manifestation, Luck"},
    {"name": "Strength", "meaning": "Fortitude, Resilience, Courage, Resolve, Confidence"},
    {"name": "The Hanged Man", "meaning": "Delay, Sacrifices, Decisions on Hold, Review, Rejection"},
    {"name": "Death", "meaning": "Change, Alteration, Loss, Boredom, Stagnation"},
    {"name": "Temperance", "meaning": "Moderation, Self-Control, Patience, Successful Combination, Economy"},
    {"name": "The Devil", "meaning": "Mystery, Magic, Strange Occurances, Fate, Catastrophe"},
    {"name": "The Tower", "meaning": "Disruption, Expulsion From Paradise, Divine Wrath, Losses, Ruined Plans"},
    {"name": "The Star", "meaning": "Bright Promise, Recovery, Hope, Good Prospects, Light at the End of the Tunnel"},
    {"name": "The Moon", "meaning": "Night Energy, Illusion, Deception, Errors, Uncertainty"},
    {"name": "The Sun", "meaning": "Daylight, Peace, Devotion, Work's Rewards, Accomplishment"},
    {"name": "Judgement", "meaning": "Rebirth, Renewal, Rejuvenation, Result, Ressurection"},
    {"name": "The World", "meaning": "Long Journey, Conclusion, Perfection, Reward, The Universe"},
    {"name": "Ace of Cups", "meaning": "Outpouring of Feelings, Fertility, Pregnancy, Seeds, Attraction"},
    {"name": "Two of Cups", "meaning": "Partnership, Two Hearts, Soul Mate, Agree, Teamwork"},
    {"name": "Three of Cups", "meaning": "Happy Gathering, Group Effort, Victory, Friends, Social Life"},
    {"name": "Four of Cups", "meaning": "Overactive Imagination, Concealed Anxiety, Satiated, Preoccupation, Aversion"},
    {"name": "Five of Cups", "meaning": "Regret, Limited Loss, Bereavement, Superficial, Lacking Love"},
    {"name": "Six of Cups", "meaning": "Past Connections, Childhood, Old Relationships, Nostalgia, Renewal"},
    {"name": "Seven of Cups", "meaning": "Nothing is as it Appears, Fantasies, Pipe Dreams, Illusions, Corruption"},
    {"name": "Eight of Cups", "meaning": "Limited Success, Modesty, Decline, Abandoned Plans, Letting Go"},
    {"name": "Nine of Cups", "meaning": "Success and Abundance, Wellbeing, Contentment, Happy and Secure, Wholeness"},
    {"name": "Ten of Cups", "meaning": "Great Joy, Pleasures of Life, Happy Family, Peace, Ecstacy"},
    {"name": "Knave of Cups", "meaning": "Imagination Coming to Life, Blonde or Fair Youth, Effeminate, Helpful, Studious"},
    {"name": "Knight of Cups", "meaning": "Advancing, Reception, Proposition, Financial Opportunity, Invitation"},
    {"name": "Queen of Cups", "meaning": "Blonde or Fair Woman, Mature, Honest, Devoted, Wise Visionary"},
    {"name": "King of Cups", "meaning": "Fair or Blonde Older Male, Responsible, Honest, Kind, Creative and Educated"},
    {"name": "Ace of Swords", "meaning": "Truth, Courage, Vision, Insight, Power"},
    {"name": "Two of Swords", "meaning": "Stalemate, Conformity, Inner Conflict, Duplicity, Disloyalty"},
    {"name": "Three of Swords", "meaning": "Disruptive Forces, Incompatibility, Heartache, Unrest, Alienation"},
    {"name": "Four of Swords", "meaning": "Mental Security, Retreat, Rest, Peace, Practical Spirit"},
    {"name": "Five of Swords", "meaning": "Greater Self Knowledge, Brute Force, Strength through Persistence, Losses from Selfishness"},
    {"name": "Six of Swords", "meaning": "Journey, Going to Warmer or Cooler Climates, Messenger, Kind Attention, Success After Trials"},
    {"name": "Seven of Swords", "meaning": "Cunning, Skillfulness, Determination, Escape from Imprisoning Situation, Awareness"},
    {"name": "Eight of Swords", "meaning": "Contradiction, Criticism, Reprimand, Crisis, Temporary Difficulties"},
    {"name": "Nine of Swords", "meaning": "Power of the Mind, Third Eye, Good Faith, Busy Mind, Suspicion"},
    {"name": "Ten of Swords", "meaning": "Affliction, Sadness, Grief, Pain, Depression"},
    {"name": "Knave of Swords", "meaning": "Spy, Observer, Intelligence, Vigilance, Examination"},
    {"name": "Knight of Swords", "meaning": "Dashing, Brave, Astute, Marksman, Skillful"},
    {"name": "Queen of Swords", "meaning": "Fault Finding, Belittling, Tough Exterior and Inner Fragility, Widow, Experiencing Hardship"},
    {"name": "King Of Swords", "meaning": "Businessman, Judge, Lawyer, Advisor, Power"},
    {"name": "Ace of Wands", "meaning": "Birth, Force, Energy, Vitality, Creativity"},
    {"name": "Two of Wands", "meaning": "Individual Success, Restlessness, Boredom, Dissatisfaction, Restraint"},
    {"name": "Three of Wands", "meaning": "Strength in Numbers, Co-Operation, Busy Times, Being on the Go, Established Strength"},
    {"name": "Four of Wands", "meaning": "Peace and Rest, Holiday, Celebration, Job Well Done, Joyful Outcome"},
    {"name": "Five of Wands", "meaning": "Competition, Battle for Life and Success, Play Acting, Conflicting Opinions, Too Many Choices"},
    {"name": "Six of Wands", "meaning": "Good News, Well-Earned Victory, Hopes and Expectations Fulfilled, Public Honors, Building Future Success"},
    {"name": "Seven of Wands", "meaning": "Resolute Firmness, Overcoming Challenges, Winning Through Fighting, Personal Strength"},
    {"name": "Eight of Wands", "meaning": "Progress, Breakthrough, Events Moving Quickly, Promises, Proposals"},
    {"name": "Nine of Wands", "meaning": "Stamina, Discipline, Strength in Adveristy, Preperation, Managing Delays"},
    {"name": "Ten of Wands", "meaning": "Success and Honors at a Price, Fulfilling Obligations while Under Pressure, Needing to Reduce Pressure"},
    {"name": "Knave of Wands", "meaning": "News and Announcements, Messages Arriving, Courageous, Loyal Friend, Decent Character"},
    {"name": "Knight of Wands", "meaning": "One the Move, Travel, Disruption, Home Modifications, New Address"},
    {"name": "Queen of Wands", "meaning": "Lady of the Manor, Charming, Magnetic, Loving and Honorable, Understanding"},
    {"name": "King of Wands", "meaning": "Estate Owner, Wise, Educated, Pillar of Community, Good Mediator"},
    {"name": "Ace of Pentacles", "meaning": "Treasure, Riches, Attainment, Recognition, Prosperity"},
    {"name": "Two of Pentacles", "meaning": "Flow of Money, Obtaining Money to Survive, Getting What you Pay for, Energy Fluctuations"},
    {"name": "Three of Pentacles", "meaning": "Mastery, Good Reputation, Expertise Based on Experience, High Status"},
    {"name": "Four of Pentacles", "meaning": "Inheritance, Gift, Benefit, Financial Protection, High Status Through Wealth"},
    {"name": "Five of Pentacles", "meaning": "Material Struggle, Health Problems, Over-Spending, Parter or Family Disloyalty, Disadvantage"},
    {"name": "Six of Pentacles", "meaning": "Good Financial Trend, Caring Acts, Generosity, Benefactor, Paying Attention"},
    {"name": "Seven of Pentacles", "meaning": "Profit, Growth, Valuable Assets, Fruits of Labor, On a Learning Curve"},
    {"name": "Eight of Pentacles", "meaning": "Apprenticeship, Employment, Commission, Planning, Calculating"},
    {"name": "Nine of Pentacles", "meaning": "Material Comfort, Abundance, Waiting for Little, Sense of Self-Worth, Safety"},
    {"name": "Ten of Pentacles", "meaning": "Wealth, Security, Family Matters, Posterity, Generations"},
    {"name": "Knave of Pentacles", "meaning": "Study, Meditation, Rule, Concentration, Love of Learning"},
    {"name": "Knight of Pentacles", "meaning": "Trustworthy Brave Man, Reliable, Decency, Able to Finish A Task, Perserverance"},
    {"name": "Queen of Pentacles", "meaning": "Dark Woman, Generosity, Riches, Assurance, Grace, Indecisive"},
    {"name": "King of Pentacles", "meaning": "Dark Man, Successful, Master, Bravery, Science, Experienced"},
]

card_positions = {
    1: "Past",
    2: "Present",
    3: "Future"
}

#Three Card Reading
def generate_three_card_reading():
    random.shuffle(tarot_deck)

    reading = {}
    for position, meaning in card_positions.items():
        card = tarot_deck.pop()
        reading[meaning] = {"card": card, "position": position}

    return reading

#Heart Reading
def generate_heart_reading():
    random.shuffle(tarot_deck)

    reading = {}
    for i in range(3):
        card = tarot_deck.pop()
        reading[f"Card {i+1}"] = {"card": card, "position": i+1}

    return reading

#Third-Eye Reading
def generate_third_eye_reading():
    random.shuffle(tarot_deck)

    reading = {}
    for i in range(3):
        card = tarot_deck.pop()
        reading[f"Insight {i+1}"] = {"card": card, "position": i+1}

    return reading

#Interpreting the Tarot Reading
def interpret_tarot_reading(reading, topic="general"):
    interpretation = []
    for meaning, info in reading.items():
        card_name = info["card"]["name"]
        position = info["position"]
    
        interpretation.append(f"In the {position} position, {card_name} indicates {info['card']['meaning']}.")
    return "\n".join(interpretation)

#Allowing The User to Input
user_input = input("Choose a Tarot Reading Type: Three Card Reading, Heart Reading, Third Eye Reading): ").lower()

if user_input == "three card reading": 
    reading = generate_three_card_reading(reading)
    print(interpretation)

elif user_input == "heart reading":
    reading = generate_heart_reading()
    interpretation = interpret_tarot_reading(reading)
    print(interpretation)

elif user_input == "third eye reading":
    reading = generate_third_eye_reading()
    interpretation = interpret_tarot_reading(reading)
    print(interpretation)

else:
    print("Invalid Input. Please choose a valid Tarot reading type. Hint: Type all Lower Case.")