i_voted(election, voter_action) ::= <<
<div align="center">
<h3>You voted with a $voter_action.votingSystem.name$ voting system.</h3>
<p>$voter_action.votingSystem.afterVotingText$</p>

$if(voter_action.votingSystem.receiptGenerated)$
  <h3>Your $voter_action.votingSystem.receiptName$:</h3>
  <p>$voter_action.votingSystem.receipt$</p>
$else$
  <p>In this system, you do not receive any paper confirmation of your vote.</p>
$endif$

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated receipts/VVPATs.</p>
<p>Note that it takes some time for votes to be tallied, and your vote may not yet appear in these totals.</p>

<h2><a href="/">Thank You for Voting!</a></h2>

</div>
>>