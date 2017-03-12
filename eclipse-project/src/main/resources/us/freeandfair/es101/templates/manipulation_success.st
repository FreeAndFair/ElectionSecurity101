manipulation_success(election, vote_change, receipt_change) ::= <<
<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

$if(vote_change)$
  $if(receipt_change)$  
    <h2>You've hacked a vote and a receipt!</h2>
  $else$
    <h2>You've hacked a vote!</h2>
  $endif$
$elseif(receipt_change)$
  <h2>You've hacked a receipt!</h2>
$else$
  <h2>You chose not to hack the vote this time.</h2>
$endif$

$tally(election.tally)$
<h4>$election.manipulatedVotePercentage$% of the $election.voteCount$ votes
cast in this election have been hacked.</h4>
<div align="center">
<button type="button" class="big_button"
 onclick="window.location.href='/'">Thanks for Participating!</button>
</div>
>>
