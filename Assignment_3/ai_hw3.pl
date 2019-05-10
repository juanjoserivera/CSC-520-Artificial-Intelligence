%facts
edge(human, ako, creature).
edge(bird, ako, creature).
edge(turkey, ako, bird).
edge(man, ako, human).
edge(frank, isa, turkey).
edge(louis, isa, man).
edge(albert, isa, man).

property(turkey, fly, no).
property(bird, fly, yes).
property(louis, legs, 1).
property(human, legs, 2).

%Base Case, find the Destination
rel(SourceNode, RelationshipType, DestinationNode):- edge(SourceNode, RelationshipType, DestinationNode).

%find parent and perform recursion in case next edge is isa
rel(SourceNode, RelationshipType, DestinationNode):- edge(SourceNode, RelationshipType, ParentNode), rel(ParentNode, isa, DestinationNode).
%find parent and perform recursion in case next edge is ako
rel(SourceNode, RelationshipType, DestinationNode):- edge(SourceNode, RelationshipType, ParentNode), rel(ParentNode, ako, DestinationNode).

%Cut to find only first ocurrence in the graph.
%hasProperty(SourceNode, Property, _), "_", it is evaluating that SourceNode has Property without taking care of Value (Only if feature exist in that node)
%"!" will cut backtracking from hasProperty(SourceNode, Property, Value) in case of fail, then we are taking the first node that has this Property in the graph
%Base Case, Source hasProperty Property
hasProperty(SourceNode, Property, Value):- property(SourceNode, Property, _), !,property(SourceNode, Property, Value).

%find parent node to check if parent hasProperty Property
hasProperty(SourceNode, Property, Value):- edge(SourceNode, isa, ParentNode), hasProperty(ParentNode, Property, Value).
hasProperty(SourceNode, Property, Value):- edge(SourceNode, ako, ParentNode), hasProperty(ParentNode, Property, Value).

%Test Cases rel
%rel(louis,isa,man).
%rel(louis,isa,human).
%rel(louis,isa,creature).
%rel(albert,isa,man).
%rel(albert,isa,human).
%rel(albert,isa,creature).
%rel(frank,isa,turkey).
%rel(frank,isa,bird).
%rel(frank,isa,creature).

%Test Cases hasProperty
%hasProperty(frank,fly,no).
%hasProperty(albert,legs,2).
%hasProperty(louis,legs,1).
