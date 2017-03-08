i_voted(election, voter_action, receipt) ::= <<
<div align="center">
<h3>You voted with: $voter_action.votingSystem.name$</h3>
<p>$voter_action.votingSystem.afterVotingText$</p>

$if(voter_action.votingSystem.receiptGenerated)$
  <h3>Your $voter_action.votingSystem.receiptName$:</h3>
  <p>$receipt$</p>
$else$
  <h3>In this system, you do not receive any confirmation of your vote.</h3>
$endif$

<br/>

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated receipts/VVPATs.</p>
<p>Note that it takes some time for votes to be tallied, and your vote may not yet appear in these totals.</p>

<h2><a href="/">Thank You for Voting! Make Sure You Get An "I Voted" Sticker!</a></h2>

</div>
>>