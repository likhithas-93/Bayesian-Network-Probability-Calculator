**Bayesian Network Probability Calculator
**
This project implements a Bayesian Network to calculate conditional probabilities of various interconnected events. The Bayesian Network is a probabilistic graphical model that represents the relationships between five key variables: Burglary (B), Earthquake (E), Alarm (A), JohnCalls (J), and MaryCalls (M). These variables are linked through conditional dependencies, which are encoded in the network's structure and probability tables. The code models these relationships and allows the user to compute probabilities for specific scenarios, including conditional probabilities given certain evidence.

The BayesianNetwork class initializes the probability tables for the variables. For example, the probability of a burglary (P(Bt)) is 0.001, while the probability of an earthquake (P(Et)) is 0.002. The alarm variable depends on both Burglary and Earthquake, with conditional probabilities like P(At|Bt,Et) = 0.95 (Alarm triggers if both Burglary and Earthquake occur). Similarly, the responses of John and Mary to the alarm are modeled with conditional probabilities, such as P(Jt|At) = 0.90 and P(Mt|At) = 0.70.

This project highlights the application of Bayesian reasoning for real-world dependencies.
