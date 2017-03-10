i_voted(election, voter_action, receipt) ::= <<
$header(election)$

<h3>You voted with: $voter_action.votingSystem.name$</h3>

<h4>$voter_action.votingSystem.afterVotingText$</h4>

$if(voter_action.votingSystem.receiptGenerated)$
  <h4>Your $voter_action.votingSystem.receiptName$:</h4>
  <h5>$receipt$</h5>
$else$
  <h4>With $voter_action.votingSystem.name$, you do not receive any
      confirmation of your vote.</h4>
$endif$

<p>$tally(election.tally)$</p>

<p>There have been $election.voteManipulationCount$ manipulated votes
and $election.receiptManipulationCount$ manipulated records.</p>

<p>Note that it takes some time for votes to be tallied, and your vote
may not yet appear in these totals.</p>

<div align="center">
<button type="button"
 onclick="window.location.href='/'">Thank You for Voting! Make Sure You Get An "I Voted" Sticker!</button>
</div>
>>