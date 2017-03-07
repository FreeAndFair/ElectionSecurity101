manipulation_success(election, vote_change, receipt_change) ::= <<
<div align="center">
<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

$if(vote_change)$
  $if(receipt_change)$  
    <h2>You've manipulated a vote and a receipt!</h2>
  $else$
    <h2>You've manipulated a vote!</h2>
  $endif$
$elseif(receipt_change)$
  <h2>You've manipulated a receipt!</h2>
$endif$

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated receipts.</p>
<h2><a href="/">Thanks for Participating!</a></h2>
</div>
>>