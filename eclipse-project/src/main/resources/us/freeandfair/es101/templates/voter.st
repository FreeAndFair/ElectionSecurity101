voter(election) ::= <<
<div align="center">
<h2>$election.name$ &ndash; $election.date$</h2>
<h3>$election.description$</h3>

<p>$voting_system_choice(election.votingSystems)$</p>
</div>
>>
