landing(election) ::= <<
$header(election)$

<div align="center">
<button type="button" class="big_button" name="Vote"
 onclick="window.location.href='/voting_system_choice'">Vote!</button>
<span style="font-size: 200%;">&nbsp;or&nbsp;</span>
<button type="button" class="big_button" name="Hack"
 onclick="window.location.href='/adversary'">Hack!</button>
</div>

<h4>$election.manipulatedVotePercentage$% of the $election.voteCount$ votes
cast in this election have been hacked.</h4>
</div>
>>
