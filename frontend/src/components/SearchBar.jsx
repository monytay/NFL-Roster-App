export default function SearchBar({players, loading, error, onClose}){
    return (
        <div className="modalBackdrop" onClick={onClose}>
            <div className="modalCard" onClick={(e) => e.stopPropagation()}>
                <div className="modalHeader">
                    <div className="modalTeam">
                        <img className="modalLogo" src="/public/logos/NFL Logo/nfl-logo-png-transparent.png"/>
                        <div className="modalTitle"><b>Players ({players.length})</b></div>
                    </div>
                    <button type="button" className="modalClose" onClick={onClose}>
                        <b>CLOSE</b>
                    </button>
                </div>

            {loading && <div>Loading</div>}
            {error && <div>Error:{error}</div>}

            {!loading && !error && players.length === 0 && (
                <div className="emptyState">
                    No players found
                </div>
                )}

            {!loading && !error && players.length > 0 && (
                <ul className="playerList">
                    {players.map((p) => (
                        <li className="playerRow" key={p.id}>
                            <div className="colNum">#{p.jerseyNumber ?? "-"}</div>
                            <div className="colPos">{p.position ?? "-"}</div>
                            <div className="colName">
                                <div className="name">{p.firstName} {p.lastName} - {p.team}</div>
                                <div className="meta">{p.college ?? "-"} - exp {p.yearsExp ?? "-"} - {p.weight}lbs - {p.height}feet</div>
                            </div>
                            <div className="colStatus">{p.status ?? "-"}</div>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    </div>
    )
}