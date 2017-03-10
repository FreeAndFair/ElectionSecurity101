adversary(election) ::= <<
$header(election)$

<p>$tally(election.tally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated records.</p>

<button type="button" class="big_button" name="Hack" 
 onclick="window.location.href='/manipulation'">Hack a Vote!</button>
>>
