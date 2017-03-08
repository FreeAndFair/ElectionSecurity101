adversary(election) ::= <<

<div align="center">
<h2>$election.name$ &ndash; $election.date$</h2>
<h3>$election.description$</h3>

<p>$tally(election.tally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated records.</p>

<button type="button" name="Hack" 
 onclick="window.location.href='/manipulation'">Hack a Vote!</button>
</div>
>>
