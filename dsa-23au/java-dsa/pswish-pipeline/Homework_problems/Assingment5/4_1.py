# 4.1 Redbook
# The Grinch is given the job of participating 2n players into the two teams of n players each.
# Each player has a numerical rating that measures how good he or she is at the game.
# The grinch seeks to divide the players as unfairly as possible, so as to create the biggest 
# possible talent imbalance between two teams. Show how the grinch can do the job in O(nlogn) time.

def grinch_teams(players):
    # Step 1: Sort players by rating
    sorted_players = sorted(players, reverse=True)

    # Step 2: Form teams from sorted list
    team_a = sorted_players[::2]
    team_b = sorted_players[1::2]

    return team_a, team_b

# Example usage:
players = [2, 87, 42, 22, 57, 6, 125, 12]
team_a, team_b = grinch_teams(players)

print("Team A:", team_a)
print("Team B:", team_b)
